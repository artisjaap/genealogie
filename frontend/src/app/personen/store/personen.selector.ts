import {createFeatureSelector, createSelector} from "@ngrx/store";
import {personenNieuwFormFeatureKey} from "./personen-nieuw-persoon-form.reducer";
import {personenInfoFeatureKey} from "./personen-persoon.reducer";
import {personenFeatureKey, PersonenState} from "./personen.reducer";
import {personenNieuwVoorRelatieFormFeatureKey} from "./persoon-nieuw-voor-relatie-form.reducer";
import {oudersVoorPersoonFormFeatureKey} from "./ouders-voor-persoon.reducer";

export const getPersonenFeatureState = createFeatureSelector<PersonenState>(personenFeatureKey);

export const getPersonenNieuwFormState = createSelector(getPersonenFeatureState, state => state[personenNieuwFormFeatureKey]);
export const getPersonenNieuwForm = createSelector(getPersonenNieuwFormState, state => state.nieuwNatuurlijkPersoon);

export const getPersoonVoorRelatieFormState = createSelector(getPersonenFeatureState, state => state[personenNieuwVoorRelatieFormFeatureKey]);
export const getPersoonVoorRelatieForm = createSelector(getPersoonVoorRelatieFormState, state => state.persoonVoorRelatie);

export const getOudersVoorPersoonFormState = createSelector(getPersonenFeatureState, state => state[oudersVoorPersoonFormFeatureKey]);
export const getOudersVoorPersoonForm = createSelector(getOudersVoorPersoonFormState, state => state.oudersVoorPersoon);


export const getPersonenState = createSelector(getPersonenFeatureState, state => state[personenInfoFeatureKey]);
export const getGevondenPersonen = createSelector(getPersonenState, state => state.gevondenPersonen);
export const getGeladenPeroonFiche = createSelector(getPersonenState, state => state.geladenPersoonFiche);
export const getGeladenDocumentTypes = createSelector(getPersonenState, state => state.documentTypes);
export const getPopupInfo = createSelector(getPersonenState, state => state.popups);
export const getToonDocumenten = createSelector(getPopupInfo, state => state.toonDocumentPopup.state);
export const getToonDocumentenData = createSelector(getPopupInfo, state => state.toonDocumentPopup.dialogData);


export const getPersoonVoorRelatie = createSelector(getPopupInfo, state => state.toonPersoonVoorRelatie.state);
export const getPersoonVoorRelatieData = createSelector(getPopupInfo, state => state.toonPersoonVoorRelatie.dialogData);
export const getOudersVoorPersoon = createSelector(getPopupInfo, state => state.toonOudersVoorPersoon.state);
export const getOudersVoorPersoonData = createSelector(getPopupInfo, state => state.toonOudersVoorPersoon.dialogData);
