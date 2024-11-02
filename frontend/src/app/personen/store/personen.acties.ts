import {createAction, props} from "@ngrx/store";
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";

export const maakNieuwNatuurlijkPersoon = createAction('[NATUURLIJK PERSOON - Nieuw natuurlijk persoon]');
export const zoekPersonen = createAction('[NATUURLIJK PERSOON - zoeken]', props<{zoekString: string}>());
export const personenGevonden = createAction('[NATUURLIJK PERSOON - gevonden]', props<{personen: NatuurlijkPersoonDto[]}>());
