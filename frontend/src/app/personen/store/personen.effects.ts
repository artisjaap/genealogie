import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import {NatuurlijkPersoonService} from "../../service/NatuurlijkPersoonService";
import {
  documentOpgeladen,
  documentTypesGeladen,
  laadDocumentTypes,
  laadNatuurlijkPersoonFiche, maakNatuurlijkPersoonVoorRelatie,
  maakNieuwNatuurlijkPersoon,
  maakOudersVanNatuurlijkPersoon, maakRelatieMet,
  natuurlijkPersoonFicheGeladen, natuurlijkPersoonVoorRelatieAangemaakt,
  nieuwNatuurlijkPersoonAangemaakt, oudersVanNatuurlijkPersoonAangemaakt,
  personenGevonden, relatieMetNatuurlijkPersoonAangemaakt,
  zoekPersonen
} from "./personen.acties";
import {catchError, EMPTY, exhaustMap, filter, map, pipe, switchMap, tap, withLatestFrom} from "rxjs";
import {Store} from "@ngrx/store";
import {PersonenState} from "./personen.reducer";
import {
  getGeladenPeroonFiche,
  getOudersVoorPersoonForm,
  getPersonenNieuwForm,
  getPersoonVoorRelatieForm, getPersoonVoorRelatieMetForm, getVoegRelatieToeMetData
} from "./personen.selector";
import {FormGroupState} from "ngrx-forms";
import {NatuurlijkPersoonFormValue} from "./personen-nieuw-persoon-form.reducer";
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";
import {ReferentieDataService} from "../../service/ReferentieDataService";
import {OudersVoorPersoonFormValue} from "./ouders-voor-persoon.reducer";
import {NatuurlijkPersoonFicheDto} from "../../model/natuurlijk-persoon-fiche-dto";
import {OudersVanKindDto} from "../../model/ouders-van-kind-dto";
import {NatuurlijkPersoonVoorRelatieFormValue} from "./persoon-nieuw-voor-relatie-form.reducer";
import {KindUitRelatieDto} from "../../model/kind-uit-relatie-dto";
import {RelatieDto, RelatieIdDto} from "../../model/relatie-dto";
import {Router} from "@angular/router";
import {PersoonRelatieMetFormValue} from "./persoon-maak-relatie-met.reducer";
import {RelatieMetNieuwNatuurlijkPersoonDto} from "../../model/relatie-met-nieuw-natuurlijk-persoon-dto";
import {DialogData} from "../../model/document-upload-data-dts";

@Injectable()
export class PersonenEffects {


  nieuwNatuurlijkPersoon$ = createEffect(() => this.actions$.pipe(
      ofType(maakNieuwNatuurlijkPersoon),
      withLatestFrom(this.store$.select(getPersonenNieuwForm)),
      exhaustMap(([data, form]:[any, FormGroupState<NatuurlijkPersoonFormValue>] ) =>
        this.natuurlijkPersoonService.natuurlijkPersoonOpslaan(this.formDataNaarDto(form.value))
        .pipe(
          map(natuurlijkPersoon => nieuwNatuurlijkPersoonAangemaakt({ natuurlijkPersoon })),
          catchError(() => EMPTY)
        ))
    )
  );



  oudersVanNatuurlijkPersoon$ = createEffect(() => this.actions$.pipe(
      ofType(maakOudersVanNatuurlijkPersoon),
      withLatestFrom(this.store$.select(getOudersVoorPersoonForm),
                     this.store$.select(getGeladenPeroonFiche)),
      exhaustMap(([data, form, persoon]:[any, FormGroupState<OudersVoorPersoonFormValue>, NatuurlijkPersoonFicheDto|undefined] ) =>
        this.natuurlijkPersoonService.oudersVanNatuurlijkPersoonToevoegen(this.oudersFormDataNaarDto(form.value, persoon))
          .pipe(
            map(genealogischDriehoekje => oudersVanNatuurlijkPersoonAangemaakt({genealogischDriehoekje})),
            catchError(() => EMPTY)
          ))
    )
  );

  persoonVoorRelatie = createEffect(() => this.actions$.pipe(
      ofType(maakNatuurlijkPersoonVoorRelatie),
      withLatestFrom(this.store$.select(getPersoonVoorRelatieForm)),
      exhaustMap(([data, form]:[{relatie: RelatieDto|null}, FormGroupState<NatuurlijkPersoonVoorRelatieFormValue>] ) =>
        this.natuurlijkPersoonService.persoonAanRelatieToevoegen(this.kindRelatieFormDataNaarDto(form.value, data.relatie))
          .pipe(
            map(genealogischDriehoekje => natuurlijkPersoonVoorRelatieAangemaakt({genealogischDriehoekje})),
            catchError(() => EMPTY)
          ))
    )
  );

  persoonMaakRelatieMiet$ = createEffect(() => this.actions$.pipe(
      ofType(maakRelatieMet),
      withLatestFrom(this.store$.select(getPersoonVoorRelatieMetForm),
        this.store$.select(getVoegRelatieToeMetData)),
      exhaustMap(([data, form, dialogData]:[any, FormGroupState<PersoonRelatieMetFormValue>, DialogData | null] ) =>
        this.natuurlijkPersoonService.maakRelatieMet(this.relatieMetNaarDto(form.value, dialogData?.natuurlijkPersoon))
          .pipe(
            map(relatie => relatieMetNatuurlijkPersoonAangemaakt({relatie})),
            catchError(() => EMPTY)
          ))
    )
  );

  refreshPersoonFiche = createEffect(() => this.actions$.pipe(
    ofType(natuurlijkPersoonVoorRelatieAangemaakt,
      oudersVanNatuurlijkPersoonAangemaakt,
      relatieMetNatuurlijkPersoonAangemaakt,
      documentOpgeladen),
    withLatestFrom(this.store$.select(getGeladenPeroonFiche)),
    filter(([data, fiche]:[any, NatuurlijkPersoonFicheDto | undefined]) => !!fiche),
    exhaustMap(([data, fiche]:[any, NatuurlijkPersoonFicheDto | undefined]) => this.natuurlijkPersoonService.laadFicheVoorNatuurlijkPersoon(fiche?fiche.natuurlijkPersoon.id:0)
      .pipe(
        map(natuurlijkPersoonFiche => natuurlijkPersoonFicheGeladen({natuurlijkPersoonFiche}))
      ))
  ));

  zoekNatuurlijkPersonen$ = createEffect(() => this.actions$.pipe(
    ofType(zoekPersonen),
    exhaustMap(data => this.natuurlijkPersoonService.zoekPersonen(data.zoekString)
      .pipe(
        map(personen => personenGevonden({personen}))
      ))
  ))

  laadNatuurlijkPersoonFiche$ = createEffect(() => this.actions$.pipe(
    ofType(laadNatuurlijkPersoonFiche),
    exhaustMap(data => this.natuurlijkPersoonService.laadFicheVoorNatuurlijkPersoon(data.natuurlijkPersoonId)
      .pipe(
        map(natuurlijkPersoonFiche => natuurlijkPersoonFicheGeladen({natuurlijkPersoonFiche}))
      ))
  ))

  laadDocumentTypes$ = createEffect(() => this.actions$.pipe(
    ofType(laadDocumentTypes),
    exhaustMap(data => this.referenceDataService.laadDocumentTypes()
      .pipe(
        map(documentTypes => documentTypesGeladen({documentTypes}))
      ))
  ))

  nieuwNatuurlijkPersoonAangemaakt = createEffect(
    () => this.actions$.pipe(
      ofType(nieuwNatuurlijkPersoonAangemaakt),
      tap((persoon) => this.router.navigateByUrl(`/personen/detail/${persoon.natuurlijkPersoon.id}`))
    ), {dispatch: false}
  );


  private formDataNaarDto(formData : NatuurlijkPersoonFormValue): NatuurlijkPersoonDto{
    return new NatuurlijkPersoonDto(0, formData.naam, formData.voornaam, formData.geslacht, new Date(formData.geborenOp), '', new Date(formData.overledenOp), '');
  }

  constructor(
    private store$: Store<PersonenState>,
    private actions$: Actions,
    private natuurlijkPersoonService: NatuurlijkPersoonService,
    private referenceDataService: ReferentieDataService,
    private router: Router
  ) {}

  private oudersFormDataNaarDto(value: OudersVoorPersoonFormValue, persoon: NatuurlijkPersoonFicheDto | undefined) {
    let moeder: NatuurlijkPersoonDto = new NatuurlijkPersoonDto(0, value.persoon1naam, value.persoon1voornaam, 'VROUW', new Date(value.persoon1geborenOp), "", new Date(value.persoon1overledenOp), "");
    let vader: NatuurlijkPersoonDto = new NatuurlijkPersoonDto(0, value.persoon2naam, value.persoon2voornaam, 'MAN', new Date(value.persoon2geborenOp), "", new Date(value.persoon2overledenOp), "");
    let kind: NatuurlijkPersoonDto|undefined = persoon?.natuurlijkPersoon;

    return new OudersVanKindDto(moeder, vader, kind);
  }

  private kindRelatieFormDataNaarDto(value: NatuurlijkPersoonVoorRelatieFormValue, relatieDto : RelatieDto | null) : KindUitRelatieDto{
    let relatie: RelatieIdDto = new RelatieIdDto(relatieDto?relatieDto.id:0);
    let natuurlijkPersoon: NatuurlijkPersoonDto = new NatuurlijkPersoonDto(0, value.naam, value.voornaam, value.geslacht, new Date(value.geborenOp), null, new Date(value.overledenOp), null);
    return new KindUitRelatieDto(relatie, natuurlijkPersoon);
  }

  private relatieMetNaarDto(value: PersoonRelatieMetFormValue, bestaandPersoon: NatuurlijkPersoonDto | null | undefined): RelatieMetNieuwNatuurlijkPersoonDto{
    debugger
    return new RelatieMetNieuwNatuurlijkPersoonDto(
      bestaandPersoon,
      new NatuurlijkPersoonDto(0, value.naam, value.voornaam, value.geslacht, new Date(value.geborenOp), "", new Date(value.overledenOp), ""),
      null,
      null
    );

  }
}
