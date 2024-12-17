import {Action, createReducer, on} from "@ngrx/store";
import {GebruikerDto} from "../../model/login-dto";
import {personenGevonden} from "../../personen/store/personen.acties";
import {PersoonDataState} from "../../personen/store/personen-persoon.reducer";
import {gebruikerIngelogged} from "./login.acties";
import {LoginState} from "./login.reducer";

export const loginPersoonFeatureKey = 'loginPersoon';

export interface LoginPersoonState {
  ingelogdeGebruiker: GebruikerDto | undefined;
}

export const loginPersoonInitialState: LoginPersoonState = {
  ingelogdeGebruiker: undefined
}

const createLoginPersoonReducer = createReducer(
  loginPersoonInitialState,
  on(gebruikerIngelogged, (state: LoginPersoonState, {gebruiker}) => ({
    ...state,
   ingelogdeGebruiker: gebruiker

  })),
);


export function theLoginPersoonReducer(state: LoginPersoonState | undefined, action: Action) {
  return createLoginPersoonReducer(state, action);
}
