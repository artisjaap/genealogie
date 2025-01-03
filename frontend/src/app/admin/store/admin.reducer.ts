import * as fromAdminReducer from "./admin-gebruiker.reducer";
import {ActionReducerMap} from "@ngrx/store";

export const adminFeatureKey = 'admin'

export interface AdminState {
  [fromAdminReducer.gebruikerFeatureKey]: fromAdminReducer.GebruikersState;
}

export const reducers: ActionReducerMap<AdminState> = {
  [fromAdminReducer.gebruikerFeatureKey]: fromAdminReducer.theGebruikerReducer,
}
