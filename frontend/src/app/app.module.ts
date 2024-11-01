import {isDevMode, NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {StoreModule} from "@ngrx/store";
import {theGenealogieReducer} from "../store/genealogie.reducer";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app.routes";
import {ReactiveFormsModule} from "@angular/forms";
import {NavigatieComponent} from "./components/navigatie/navigatie.component";
import {InstellingenComponent} from "./components/instellingen/instellingen.component";
import {PersoonNieuwFicheComponent} from "./components/persoon-nieuw-fiche/persoon-nieuw-fiche.component";
import {PersoonFicheComponent} from "./components/persoon-fiche/persoon-fiche.component";
import {PersoonInfoFormComponent} from "./components/persoon-info-form/persoon-info-form.component";
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from "@angular/material/form-field";
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {MAT_DATE_LOCALE} from "@angular/material/core";
import {NgrxFormsModule} from "ngrx-forms";
import {EffectsModule} from "@ngrx/effects";
import {GenealogieEffects} from "../store/genealogie.effects";
import { provideHttpClient} from "@angular/common/http";

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    StoreModule.forRoot({genealogie: theGenealogieReducer}),
    EffectsModule.forRoot([GenealogieEffects]),
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: !isDevMode()}),
    NgrxFormsModule,
    NavigatieComponent,
    InstellingenComponent,
    PersoonNieuwFicheComponent,
    PersoonFicheComponent,
    PersoonInfoFormComponent,

  ],
  providers: [
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'outline'}},
    {provide: MAT_DATE_LOCALE, useValue: 'nl-BE'},
    provideAnimationsAsync(),
    provideHttpClient()
],
  bootstrap: [AppComponent]
})
export class AppModule {

}
