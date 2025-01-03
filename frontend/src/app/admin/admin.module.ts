import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {AdminRoutingModule} from "./admin-routing.module";
import {StoreModule} from "@ngrx/store";
import * as adminReducer from "./store/admin.reducer";
import {EffectsModule} from "@ngrx/effects";
import {AdminEffects} from "./store/admin.effects";
import {GebruikerService} from "./service/GebruikerService";

@NgModule({
  declarations: [],
  providers: [GebruikerService],
  imports: [
    CommonModule,
    AdminRoutingModule,
    StoreModule.forFeature(adminReducer.adminFeatureKey, adminReducer.reducers),
    EffectsModule.forFeature(AdminEffects),
  ],
})
export class AdminModule {

}
