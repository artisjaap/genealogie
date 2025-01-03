import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {GebruikersComponent} from "./components/gebruikers/gebruikers.component";

const routes: Routes = [
  {
    path: "gebruikers",
    component: GebruikersComponent,
  },
];

@NgModule({
  providers: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {

}
