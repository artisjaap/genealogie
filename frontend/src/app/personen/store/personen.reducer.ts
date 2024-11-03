import * as fromPersonenReducer from './personen-persoon.reducer'
import * as fromPersonenNieuwFormReducer from './personen-nieuw-persoon-form.reducer'
import {ActionReducerMap} from '@ngrx/store';

export const personenFeatureKey = 'personen'

export interface PersonenState {
  [fromPersonenReducer.personenInfoFeatureKey]: fromPersonenReducer.PersoonDataState;
  [fromPersonenNieuwFormReducer.personenNieuwFormFeatureKey]: fromPersonenNieuwFormReducer.PersonenNieuwFormState;
}

export const reducers: ActionReducerMap<PersonenState> = {
  [fromPersonenReducer.personenInfoFeatureKey]: fromPersonenReducer.thePersonenReducer,
  [fromPersonenNieuwFormReducer.personenNieuwFormFeatureKey]: fromPersonenNieuwFormReducer.thePersonenNieuwFormReducer,

}


