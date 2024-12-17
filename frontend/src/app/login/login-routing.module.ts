import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./login/login.component";
import {RegistreerComponent} from "./registreer/registreer.component";

const routes: Routes = [
  {
    path: "",
    pathMatch: "full",
    component: LoginComponent,
  },
  {
    path: "registreer",
    component: RegistreerComponent,
  }
];

@NgModule({
  providers: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule {

}
