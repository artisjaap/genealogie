import * as fromPersonenReducer from './personen-persoon.reducer'
import {thePersonenFeatureKey} from './personen-persoon.reducer'
import * as fromPersonenNieuwFormReducer from './personen-nieuw-persoon-form.reducer'
import {personenNieuwFormFeatureKey} from './personen-nieuw-persoon-form.reducer'
import {ActionReducerMap, createFeatureSelector, createSelector} from '@ngrx/store';

export const personenFeatureKey = 'personen'

export interface PersonenState {
  [fromPersonenReducer.thePersonenFeatureKey]: fromPersonenReducer.PersonenState;
  [fromPersonenNieuwFormReducer.personenNieuwFormFeatureKey]: fromPersonenNieuwFormReducer.PersonenNieuwFormState;
}

export const reducers: ActionReducerMap<PersonenState> = {
  [fromPersonenReducer.thePersonenFeatureKey]: fromPersonenReducer.thePersonenReducer,
  [fromPersonenNieuwFormReducer.personenNieuwFormFeatureKey]: fromPersonenNieuwFormReducer.thePersonenNieuwFormReducer,

}


export const getPersonenFeatureState = createFeatureSelector<PersonenState>(personenFeatureKey);

export const getPersonenNieuwFormState = createSelector(getPersonenFeatureState, state => state[personenNieuwFormFeatureKey]);
export const getPersonenState = createSelector(getPersonenFeatureState, state => state[thePersonenFeatureKey]);
