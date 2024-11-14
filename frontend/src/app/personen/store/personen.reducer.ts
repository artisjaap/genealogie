import * as fromPersonenReducer from './personen-persoon.reducer'
import * as fromPersonenNieuwFormReducer from './personen-nieuw-persoon-form.reducer'
import * as fromPersoonVoorRelatieFormReducer from './persoon-nieuw-voor-relatie-form.reducer'
import * as fromOudersVoorPersoonFormReducer from './ouders-voor-persoon.reducer'
import * as fromPersoonMaakRelatieMetFormReducer from './persoon-maak-relatie-met.reducer'
import {ActionReducerMap} from '@ngrx/store';

export const personenFeatureKey = 'personen'

export interface PersonenState {
  [fromPersonenReducer.personenInfoFeatureKey]: fromPersonenReducer.PersoonDataState;
  [fromPersonenNieuwFormReducer.personenNieuwFormFeatureKey]: fromPersonenNieuwFormReducer.PersonenNieuwFormState;
  [fromPersoonVoorRelatieFormReducer.personenNieuwVoorRelatieFormFeatureKey]: fromPersoonVoorRelatieFormReducer.PersonenNieuwVoorRelatieFormState;
  [fromOudersVoorPersoonFormReducer.oudersVoorPersoonFormFeatureKey]: fromOudersVoorPersoonFormReducer.OudersVoorPersoonFormState;
  [fromPersoonMaakRelatieMetFormReducer.persoonMaakRelatieMetFeatureKey]: fromPersoonMaakRelatieMetFormReducer.PersoonRelatieMetFormState;
}

export const reducers: ActionReducerMap<PersonenState> = {
  [fromPersonenReducer.personenInfoFeatureKey]: fromPersonenReducer.thePersonenReducer,
  [fromPersonenNieuwFormReducer.personenNieuwFormFeatureKey]: fromPersonenNieuwFormReducer.thePersonenNieuwFormReducer,
  [fromPersoonVoorRelatieFormReducer.personenNieuwVoorRelatieFormFeatureKey]: fromPersoonVoorRelatieFormReducer.thePersonenNieuwFormReducer,
  [fromOudersVoorPersoonFormReducer.oudersVoorPersoonFormFeatureKey]: fromOudersVoorPersoonFormReducer.thOudersVoorPersoonFormReducer,
  [fromPersoonMaakRelatieMetFormReducer.persoonMaakRelatieMetFeatureKey]: fromPersoonMaakRelatieMetFormReducer.thePersoonRelatieMetFormReducer
}


