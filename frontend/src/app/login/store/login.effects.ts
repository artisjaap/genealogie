import {Injectable} from "@angular/core";
import {Actions, createEffect, ofType} from "@ngrx/effects";
import * as acties from "./login.acties";
import {exhaustMap, map, tap, withLatestFrom} from "rxjs";
import {Action, Store} from "@ngrx/store";
import {LoginState} from "./login.reducer";
import {LoginService} from "../service/LoginService";
import {Router} from "@angular/router";


@Injectable()
export class LoginEffects {
  registreerGebruiker = createEffect(() => this.actions$.pipe(
    ofType(acties.maakNieuweGebruikerAan),
    exhaustMap(payload => this.loginService.registreerGebruiker(payload.nieuweGebruiker)
      .pipe(
        map(loginUser => acties.nieuweGebruikerAangemaakt({loginUser}))
      ))
  ));

  loginGebruiker = createEffect(() => this.actions$.pipe(
    ofType(acties.loginVoorGebruiker),
    exhaustMap(payload => this.loginService.login(payload.login)
      .pipe(
        map(loginUser => acties.gebruikerIngelogged({loginUser}))
      ))
  ));


  redirectNaLogin = createEffect(
    () => this.actions$.pipe(
      ofType(acties.gebruikerIngelogged),
      tap((action: Action) => {
        this.router.navigateByUrl('/');
      })
    ), {dispatch: false});


  constructor(
    private store$: Store<LoginState>,
    private router: Router,
    private actions$: Actions,
    private loginService: LoginService,
  ) {
  }
}
