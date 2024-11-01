import {RouterModule, Routes} from '@angular/router';
import {InstellingenComponent} from "./components/instellingen/instellingen.component";
import {PersonenZoekenComponent} from "./components/personen-zoeken/personen-zoeken.component";
import {PersoonNieuwFicheComponent} from "./components/persoon-nieuw-fiche/persoon-nieuw-fiche.component";
import {NgModule} from "@angular/core";

export const routes: Routes = [

  {
    path: 'instellingen',
    component: InstellingenComponent
  },
  {
    path: 'zoeken',
    component: PersonenZoekenComponent
  },
  {
    path: 'persoon-nieuw',
    component: PersoonNieuwFicheComponent
  },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
