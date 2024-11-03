import {Action, createReducer, on} from '@ngrx/store';
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";
import {natuurlijkPersoonFicheGeladen, personenGevonden} from "./personen.acties";
import {NatuurlijkPersoonFicheDto} from "../../model/natuurlijk-persoon-fiche-dto";

export const personenInfoFeatureKey = 'personenInfo';


export interface PersoonDataState {
  gevondenPersonen: NatuurlijkPersoonDto[],
  geladenPersoonFiche: NatuurlijkPersoonFicheDto | undefined,

}

export const personenInitialState: PersoonDataState = {
  gevondenPersonen: [],
  geladenPersoonFiche: undefined,
};


const createThePersonenReducer = createReducer(
  personenInitialState,
  on(personenGevonden, (state: PersoonDataState, {personen}) => ({
    ...state,
    gevondenPersonen: personen

  })),
  on(natuurlijkPersoonFicheGeladen, (state: PersoonDataState, {natuurlijkPersoonFiche}) => ({
    ...state,
    geladenPersoonFiche: natuurlijkPersoonFiche

  })),
);

export function thePersonenReducer(state: PersoonDataState | undefined, action: Action) {
  return createThePersonenReducer(state, action);
}
