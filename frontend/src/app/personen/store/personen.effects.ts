import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {NatuurlijkPersoonService} from "../../service/NatuurlijkPersoonService";
import {maakNieuwNatuurlijkPersoon, personenGevonden, zoekPersonen} from "./personen.acties";
import {catchError, EMPTY, exhaustMap, map, withLatestFrom} from "rxjs";
import {Store} from "@ngrx/store";
import {PersonenState} from "./personen.reducer";
import {getPersonenNieuwForm} from "./personen.selector";
import {FormGroupState} from "ngrx-forms";
import {NatuurlijkPersoonFormValue} from "./personen-nieuw-persoon-form.reducer";
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";

@Injectable()
export class PersonenEffects {


  nieuwNatuurlijkPersoon$ = createEffect(() => this.actions$.pipe(
      ofType(maakNieuwNatuurlijkPersoon),
      withLatestFrom(this.store$.select(getPersonenNieuwForm)),

      exhaustMap(([data, form]:[any, FormGroupState<NatuurlijkPersoonFormValue>] ) =>
        this.natuurlijkPersoonService.natuurlijkPersoonOpslaan(this.formDataNaarDto(form.value))
        .pipe(
          map(movies => ({ type: '[Movies API] Movies Loaded Success', payload: movies })),
          catchError(() => EMPTY)
        ))
    )
  );

  zoekNatuurlijkPersonen$ = createEffect(() => this.actions$.pipe(
    ofType(zoekPersonen),
    exhaustMap(data => this.natuurlijkPersoonService.zoekPersonen(data.zoekString)
      .pipe(
        map(personen => personenGevonden({personen}))
      ))
  ))

  private formDataNaarDto(formData : NatuurlijkPersoonFormValue): NatuurlijkPersoonDto{
    return new NatuurlijkPersoonDto(0, formData.naam, formData.voornaam, new Date(formData.geborenOp), '', new Date(formData.overledenOp), '');
  }

  constructor(
    private store$: Store<PersonenState>,
    private actions$: Actions,
    private natuurlijkPersoonService: NatuurlijkPersoonService
  ) {}
}
