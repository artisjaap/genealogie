import {createAction, props} from "@ngrx/store";
import {GebruikerDto, LoginDto, RegistreerGebruikerDto} from "../../model/login-dto";

export const maakNieuweGebruikerAan = createAction('[LOGIN - maak gebruiker aan]', props<{nieuweGebruiker: RegistreerGebruikerDto}>());
export const nieuweGebruikerAangemaakt = createAction('[LOGIN - gebruiker aangemaakt]', props<{gebruiker: GebruikerDto}>());

export const loginVoorGebruiker = createAction('[LOGIN - login voor gebruiker]', props<{login: LoginDto}>());
export const gebruikerIngelogged = createAction('[LOGIN - gebruiker ingelogged]', props<{gebruiker: GebruikerDto}>());
