import * as fromLoginPersoonReducer from "./login-gebruiker.reducer";
import {ActionReducerMap} from "@ngrx/store";

export const loginFeatureKey = 'login'

export interface LoginState {
  [fromLoginPersoonReducer.loginPersoonFeatureKey]: fromLoginPersoonReducer.LoginPersoonState;
}

export const reducers: ActionReducerMap<LoginState> = {
  [fromLoginPersoonReducer.loginPersoonFeatureKey]: fromLoginPersoonReducer.theLoginPersoonReducer,
}


