import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as acties from "./login.acties";
import {exhaustMap, map} from "rxjs";
import {Store} from "@ngrx/store";
import {LoginState} from "./login.reducer";
import {LoginService} from "../service/LoginService";


@Injectable()
export class LoginEffects {
  registreerGebruiker = createEffect(() => this.actions$.pipe(
    ofType(acties.maakNieuweGebruikerAan),
    exhaustMap(payload => this.loginService.registreerGebruiker(payload.nieuweGebruiker)
      .pipe(
        map(gebruiker => acties.nieuweGebruikerAangemaakt({gebruiker}))
      ))
  ));

  loginGebruiker = createEffect(() => this.actions$.pipe(
    ofType(acties.loginVoorGebruiker),
    exhaustMap(payload => this.loginService.login(payload.login)
      .pipe(
        map(gebruiker => acties.gebruikerIngelogged({gebruiker}))
      ))
  ));

  constructor(
    private store$: Store<LoginState>,
    private actions$: Actions,
    private loginService: LoginService,
  ) {}
}
