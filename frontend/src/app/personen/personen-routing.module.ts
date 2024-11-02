import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PersonenZoekenComponent} from "./components/personen-zoeken/personen-zoeken.component";
import {PersoonNieuwFicheComponent} from "./components/persoon-nieuw-fiche/persoon-nieuw-fiche.component";

const routes: Routes = [
  {
    path: "zoeken",
    component: PersonenZoekenComponent
  },
  {
    path: "nieuw",
    component: PersoonNieuwFicheComponent
  }
];

@NgModule({
  providers: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonenRoutingModule {

}
