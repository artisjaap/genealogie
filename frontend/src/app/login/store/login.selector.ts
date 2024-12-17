import {createFeatureSelector, createSelector} from "@ngrx/store";
import {loginPersoonFeatureKey} from "./login-gebruiker.reducer";
import {loginFeatureKey, LoginState} from "./login.reducer";

export const getLoginState = createFeatureSelector<LoginState>(loginFeatureKey);
export const getGebruikerState = createSelector(getLoginState, state => state[loginPersoonFeatureKey]);
export const getIngelogdeGebruiker = createSelector(getGebruikerState, state => state.ingelogdeGebruiker);
export const getIsGebruikerIngelogged = createSelector(getIngelogdeGebruiker, state => !!state);
export const getToken = createSelector(getIngelogdeGebruiker, state => state?.token);
