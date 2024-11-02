import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {PersonenZoekenComponent} from "./components/personen-zoeken/personen-zoeken.component";
import {PersoonNieuwFicheComponent} from "./components/persoon-nieuw-fiche/persoon-nieuw-fiche.component";
import {PersoonFicheComponent} from "./components/persoon-fiche/persoon-fiche.component";

const routes: Routes = [
  {
    path: "zoeken",
    component: PersonenZoekenComponent
  },
  {
    path: "nieuw",
    component: PersoonNieuwFicheComponent
  },
  {
    path: "detail/:id",
    component: PersoonFicheComponent
  }
];

@NgModule({
  providers: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PersonenRoutingModule {

}
