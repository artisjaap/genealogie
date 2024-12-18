import {Action, createReducer, on} from "@ngrx/store";
import {LoginResponseDto} from "../../model/login-dto";
import {gebruikerIngelogged, nieuweGebruikerAangemaakt} from "./login.acties";

export const loginPersoonFeatureKey = 'loginPersoon';

export interface LoginPersoonState {
  ingelogdeGebruiker: LoginResponseDto | undefined;
}

export const loginPersoonInitialState: LoginPersoonState = {
  ingelogdeGebruiker: undefined
}

const createLoginPersoonReducer = createReducer(
  loginPersoonInitialState,
  on(gebruikerIngelogged, (state: LoginPersoonState, {loginUser}) => ({
    ...state,
    ingelogdeGebruiker: loginUser

  })),
  on(nieuweGebruikerAangemaakt, (state: LoginPersoonState, {loginUser}) => ({
    ...state,
    ingelogdeGebruiker: loginUser

  })),
);


export function theLoginPersoonReducer(state: LoginPersoonState | undefined, action: Action) {
  return createLoginPersoonReducer(state, action);
}
