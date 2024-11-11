import {isDevMode, NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {StoreModule} from "@ngrx/store";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app.routes";
import {ReactiveFormsModule} from "@angular/forms";
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from "@angular/material/form-field";
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {MAT_DATE_LOCALE} from "@angular/material/core";
import {NgrxFormsModule} from "ngrx-forms";
import {EffectsModule} from "@ngrx/effects";
import {provideHttpClient} from "@angular/common/http";
import {PersonenModule} from "./personen/personen.module";
import {AdminModule} from "./admin/admin.module";
import {NavigatieComponent} from "./components/navigatie/navigatie.component";
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    StoreModule.forRoot(),
    EffectsModule.forRoot([]),
    StoreDevtoolsModule.instrument({maxAge: 25, logOnly: !isDevMode()}),
    NgrxFormsModule,
    PersonenModule,
    AdminModule,
    NavigatieComponent,
    FontAwesomeModule,

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
