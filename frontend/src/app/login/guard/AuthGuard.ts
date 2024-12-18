import {ActivatedRouteSnapshot, Router} from "@angular/router";
import {LoginState} from "../store/login.reducer";
import * as LoginSelector from "../store/login.selector";
import {select, Store} from "@ngrx/store";
import {inject} from "@angular/core";
import {tap} from "rxjs";

export const isIngelogged = (route: ActivatedRouteSnapshot) => {
  const store = inject(Store<{ app: LoginState }>);
  const router = inject(Router);


  return store.pipe(
    select(LoginSelector.getIsGebruikerIngelogged),
    tap(isIngelogged => {
      if (!isIngelogged) {
        router.navigate(["/login"]);
      }
    })
  );

}
