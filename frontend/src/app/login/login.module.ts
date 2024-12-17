import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {LoginRoutingModule} from "./login-routing.module";
import {StoreModule} from "@ngrx/store";
import * as loginReducer from "./store/login.reducer";
import {EffectsModule} from "@ngrx/effects";
import {LoginEffects} from "./store/login.effects";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    LoginRoutingModule,
    StoreModule.forFeature(loginReducer.loginFeatureKey, loginReducer.reducers),
    EffectsModule.forFeature(LoginEffects),

  ],
  providers: []
})
export class LoginModule {

}
