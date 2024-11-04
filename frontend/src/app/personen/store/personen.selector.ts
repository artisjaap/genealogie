import {createFeatureSelector, createSelector} from "@ngrx/store";
import {personenNieuwFormFeatureKey} from "./personen-nieuw-persoon-form.reducer";
import {personenInfoFeatureKey} from "./personen-persoon.reducer";
import {personenFeatureKey, PersonenState} from "./personen.reducer";

export const getPersonenFeatureState = createFeatureSelector<PersonenState>(personenFeatureKey);

export const getPersonenNieuwFormState = createSelector(getPersonenFeatureState, state => state[personenNieuwFormFeatureKey]);
export const getPersonenNieuwForm = createSelector(getPersonenNieuwFormState, state => state.nieuwNatuurlijkPersoon);


export const getPersonenState = createSelector(getPersonenFeatureState, state => state[personenInfoFeatureKey]);
export const getGevondenPersonen = createSelector(getPersonenState, state => state.gevondenPersonen);
export const getGeladenPeroonFiche = createSelector(getPersonenState, state => state.geladenPersoonFiche);
export const getGeladenDocumentTypes = createSelector(getPersonenState, state => state.documentTypes);
