import {Action, createReducer, on} from '@ngrx/store';
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";
import {personenGevonden} from "./personen.acties";

export const personenInfoFeatureKey = 'personenInfo';


export interface PersonenState {
  gevondenPersonen: NatuurlijkPersoonDto[]

}

export const personenInitialState: PersonenState = {
  gevondenPersonen: []
};


const createThePersonenReducer = createReducer(
  personenInitialState,
  on(personenGevonden, (state: PersonenState, {personen}) => ({
    ...state,
    gevondenPersonen: personen

  })),
);

export function thePersonenReducer(state: PersonenState | undefined, action: Action) {
  return createThePersonenReducer(state, action);
}
