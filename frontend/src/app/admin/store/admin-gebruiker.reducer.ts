import {Action, createReducer} from '@ngrx/store';

export const gebruikerFeatureKey = 'gebruikers';


export interface GebruikersState {

}

export const gebruikersInitialState: GebruikersState = {
};


const createTheGebruikersReducer = createReducer(
  gebruikersInitialState,
);

export function theGebruikerReducer(state: GebruikersState | undefined, action: Action) {
  return createTheGebruikersReducer(state, action);
}
