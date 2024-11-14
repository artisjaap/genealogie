import {Component} from '@angular/core';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {
  MatDatepicker,
  MatDatepickerInput,
  MatDatepickerModule,
  MatDatepickerToggle
} from "@angular/material/datepicker";
import {MAT_DATE_FORMATS, provideNativeDateAdapter} from "@angular/material/core";
import {Store} from "@ngrx/store";
import {FormGroupState, NgrxFormsModule, NgrxValueConverter, NgrxValueConverters} from "ngrx-forms";
import {Observable} from "rxjs";
import {AsyncPipe, NgIf} from "@angular/common";
import {NatuurlijkPersoonFormValue} from "../../store/personen-nieuw-persoon-form.reducer";
import {maakNieuwNatuurlijkPersoon} from "../../store/personen.acties";
import {getPersonenNieuwForm} from "../../store/personen.selector";
import {MatButtonModule} from "@angular/material/button";
import {MatRadioModule} from "@angular/material/radio";
import {DateToUtcConverter} from "../../../model/date-to-utc-converter";
import {provideMomentDateAdapter} from "@angular/material-moment-adapter";

@Component({
  selector: 'app-persoon-nieuw-fiche',
  standalone: true,
  providers: [],

  imports: [MatFormFieldModule, MatRadioModule, MatInputModule,MatButtonModule, MatIconModule, MatDatepickerModule, AsyncPipe, NgrxFormsModule, NgIf],
  templateUrl: './persoon-nieuw-fiche.component.html',
  styleUrl: './persoon-nieuw-fiche.component.scss'
})
export class PersoonNieuwFicheComponent {
  formState$: Observable<FormGroupState<NatuurlijkPersoonFormValue>>;

  constructor(private store: Store<any>) {
    this.formState$ = store.select(getPersonenNieuwForm);
  }

  // dateValueConverter = new DateToUtcConverter();
  dateValueConverter = NgrxValueConverters.dateToISOString;

  opslaan() {
    this.store.dispatch(maakNieuwNatuurlijkPersoon());
  }
}


