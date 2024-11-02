import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {PersonenRoutingModule} from "./personen-routing.module";
import {StoreModule} from "@ngrx/store";
import * as personenReducer from './store/personen.reducer';
import {PersonenZoekenComponent} from "./components/personen-zoeken/personen-zoeken.component";
import {EffectsModule} from "@ngrx/effects";
import {PersonenEffects} from "./store/personen.effects";

@NgModule({
  declarations: [

  ],
  imports: [
    CommonModule,
    PersonenRoutingModule,
    StoreModule.forFeature(personenReducer.personenFeatureKey, personenReducer.reducers),
    EffectsModule.forFeature(PersonenEffects),
    PersonenZoekenComponent,
  ]
})
export class PersonenModule {

}
