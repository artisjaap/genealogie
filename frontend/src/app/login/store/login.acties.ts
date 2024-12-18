import {createAction, props} from "@ngrx/store";
import {LoginUserResponseDto, LoginDto, RegistreerGebruikerDto, LoginResponseDto} from "../../model/login-dto";

export const maakNieuweGebruikerAan = createAction('[LOGIN - maak gebruiker aan]', props<{nieuweGebruiker: RegistreerGebruikerDto}>());
export const nieuweGebruikerAangemaakt = createAction('[LOGIN - gebruiker aangemaakt]', props<{loginUser: LoginResponseDto}>());

export const loginVoorGebruiker = createAction('[LOGIN - login voor gebruiker]', props<{login: LoginDto}>());
export const gebruikerIngelogged = createAction('[LOGIN - gebruiker ingelogged]', props<{loginUser: LoginResponseDto}>());
