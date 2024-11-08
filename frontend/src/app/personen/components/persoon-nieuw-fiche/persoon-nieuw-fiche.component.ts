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
import {provideNativeDateAdapter} from "@angular/material/core";
import {Store} from "@ngrx/store";
import {FormGroupState, NgrxFormsModule, NgrxValueConverters} from "ngrx-forms";
import {Observable} from "rxjs";
import {AsyncPipe, NgIf} from "@angular/common";
import {NatuurlijkPersoonFormValue} from "../../store/personen-nieuw-persoon-form.reducer";
import {maakNieuwNatuurlijkPersoon} from "../../store/personen.acties";
import {getPersonenNieuwForm} from "../../store/personen.selector";
import {MatButtonModule} from "@angular/material/button";

@Component({
  selector: 'app-persoon-nieuw-fiche',
  standalone: true,
  providers: [provideNativeDateAdapter()],

  imports: [MatFormFieldModule, MatInputModule,MatButtonModule, MatIconModule, MatDatepickerModule, MatDatepickerInput, MatDatepickerToggle, MatDatepicker, AsyncPipe, NgrxFormsModule, NgIf],
  templateUrl: './persoon-nieuw-fiche.component.html',
  styleUrl: './persoon-nieuw-fiche.component.scss'
})
export class PersoonNieuwFicheComponent {
  formState$: Observable<FormGroupState<NatuurlijkPersoonFormValue>>;

  constructor(private store: Store<any>) {
    this.formState$ = store.select(getPersonenNieuwForm);
  }

  dateValueConverter = NgrxValueConverters.dateToISOString;

  opslaan() {
    this.store.dispatch(maakNieuwNatuurlijkPersoon());
  }
}


