import {createAction, props} from "@ngrx/store";
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";
import {NatuurlijkPersoonFicheDto} from "../../model/natuurlijk-persoon-fiche-dto";

export const maakNieuwNatuurlijkPersoon = createAction('[NATUURLIJK PERSOON - Nieuw natuurlijk persoon]');
export const zoekPersonen = createAction('[NATUURLIJK PERSOON - zoeken]', props<{zoekString: string}>());
export const personenGevonden = createAction('[NATUURLIJK PERSOON - gevonden]', props<{personen: NatuurlijkPersoonDto[]}>());


export const laadNatuurlijkPersoonFiche = createAction('[NATUURLIJK PERSOON - Laad fiche]', props<{natuurlijkPersoonId: number}>());
export const natuurlijkPersoonFicheGeladen = createAction('[NATUURLIJK PERSOON - fiche geladen]', props<{natuurlijkPersoonFiche: NatuurlijkPersoonFicheDto}>());
