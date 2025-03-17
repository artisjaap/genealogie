import {isDevMode, NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {StoreModule} from "@ngrx/store";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutingModule} from "./app.routes";
import {ReactiveFormsModule} from "@angular/forms";
import {StoreDevtoolsModule} from "@ngrx/store-devtools";
import {MAT_FORM_FIELD_DEFAULT_OPTIONS} from "@angular/material/form-field";
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {NgrxFormsModule} from "ngrx-forms";
import {EffectsModule} from "@ngrx/effects";
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptors} from "@angular/common/http";
import {PersonenModule} from "./personen/personen.module";
import {AdminModule} from "./admin/admin.module";
import {NavigatieComponent} from "./components/navigatie/navigatie.component";
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {provideMomentDateAdapter} from "@angular/material-moment-adapter";
import {popperVariation, provideTippyConfig, tooltipVariation} from "@ngneat/helipopper";
import {LoginModule} from "./login/login.module";
import {authInterceptor} from "./service/HttpInterceptor";
import {SvgIconComponent} from "./components/common/svg-icon/svg-icon.component";

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
    LoginModule,
    AdminModule,
    NavigatieComponent,
    FontAwesomeModule,
    SvgIconComponent,

  ],
  providers: [
    {provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: {appearance: 'outline'}},
    provideMomentDateAdapter({
      parse: {
        dateInput: "DD/MM/YYYY"
      },
      display: {
        dateInput: "DD/MM/YYYY",
        monthYearLabel: 'MMM YYYY',
        dateA11yLabel:'LL',
        monthYearA11yLabel: 'MMM YYYY'
      }}, {useUtc: true}),
    provideTippyConfig({
      defaultVariation: 'tooltip',
      variations: {
        tooltip: tooltipVariation,
        popper: popperVariation,
      },
    }),
    provideAnimationsAsync(),
    provideHttpClient(withInterceptors([authInterceptor]))
],
  bootstrap: [AppComponent]
})
export class AppModule {

}
