import { Routes } from '@angular/router';
import {InstellingenComponent} from "./components/instellingen/instellingen.component";
import {PersonenZoekenComponent} from "./components/personen-zoeken/personen-zoeken.component";
import {PersoonNieuwFicheComponent} from "./components/persoon-nieuw-fiche/persoon-nieuw-fiche.component";

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
