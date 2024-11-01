import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {NatuurlijkPersoonService} from "../app/service/NatuurlijkPersoonService";
import {maakNieuwNatuurlijkPersoon} from "./genealogie.acties";
import {catchError, EMPTY, exhaustMap, map, withLatestFrom} from "rxjs";

@Injectable()
export class GenealogieEffects {


  nieuwNatuurlijkPersoon$ = createEffect(() => this.actions$.pipe(
      ofType(maakNieuwNatuurlijkPersoon),
      exhaustMap(() => this.natuurlijkPersoonService.natuurlijkPersoonOpslaan(null)
        .pipe(
          map(movies => ({ type: '[Movies API] Movies Loaded Success', payload: movies })),
          catchError(() => EMPTY)
        ))
    )
  );

  constructor(
    private actions$: Actions,
    private natuurlijkPersoonService: NatuurlijkPersoonService
  ) {}
}
