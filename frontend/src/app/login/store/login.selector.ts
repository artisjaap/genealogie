import {createFeatureSelector, createSelector} from "@ngrx/store";
import {loginPersoonFeatureKey} from "./login-gebruiker.reducer";
import {loginFeatureKey, LoginState} from "./login.reducer";

export const getLoginState = createFeatureSelector<LoginState>(loginFeatureKey);
export const getGebruikerState = createSelector(getLoginState, state => state[loginPersoonFeatureKey]);
export const getLoginGebruiker = createSelector(getGebruikerState, state => state.ingelogdeGebruiker);
export const getIngelogdeGebruiker = createSelector(getLoginGebruiker, state => state?.gebruiker);
export const getIsGebruikerIngelogged = createSelector(getIngelogdeGebruiker, state => !!state);
export const getToken = createSelector(getLoginGebruiker, state => state?.token);
