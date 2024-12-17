import {inject, Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest} from "@angular/common/http";
import {Store} from "@ngrx/store";
import {LoginState} from "../login/store/login.reducer";
import * as LoginSelector from "../login/store/login.selector";
import {map, Observable, switchMap, take} from "rxjs";

/*@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private store: Store<LoginState>) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    console.log("Intercept");
    return this.store.select(LoginSelector.getToken).pipe(
      take(1), // Take the latest value and complete
      map(token => token ? `Bearer ${token}` : ''), // Format the token if present
      switchMap(authToken => {
        // Clone the request and attach the Authorization header
        console.log("With token: " + authToken);
        const authReq = req.clone({
          headers: req.headers.set('Authorization', authToken)
        });

        // Pass the cloned request to the next handler
        return next.handle(authReq);
      })
    );
  }

}*/

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const store = inject(Store<LoginState>);

  return store.select(LoginSelector.getToken).pipe(
    take(1), // Take the latest value and complete
    map(token => token ? `Bearer ${token}` : ''), // Format the token if present
    switchMap(authToken => {
      // Clone the request and attach the Authorization header
      const authReq = req.clone({
        headers: req.headers.set('Authorization', authToken)
      });

      // Pass the cloned request to the next handler
      return next(authReq);
    })
  );


};
