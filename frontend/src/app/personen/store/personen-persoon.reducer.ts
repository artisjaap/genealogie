import {Action, createReducer} from '@ngrx/store';

export const thePersonenFeatureKey = 'thePersonen';


export interface PersonenState {


}

export const thePersonennitialState: PersonenState = {

};


const createThePersonenReducer = createReducer(
  thePersonennitialState,


);

export function thePersonenReducer(state: PersonenState | undefined, action: Action) {
  return createThePersonenReducer(state, action);
}
