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
import {NatuurlijkPersoonFormValue, nieuwNatuurlijkPersoonForm} from "../../../store/genealogie.reducer";
import {FormGroupState, NgrxFormsModule, NgrxValueConverters} from "ngrx-forms";
import {map, Observable, tap} from "rxjs";
import {AsyncPipe, NgIf} from "@angular/common";
import {maakNieuwNatuurlijkPersoon} from "../../../store/genealogie.acties";

@Component({
  selector: 'app-persoon-nieuw-fiche',
  standalone: true,
  providers: [provideNativeDateAdapter()],

  imports: [MatFormFieldModule, MatInputModule, MatIconModule, MatDatepickerModule, MatDatepickerInput, MatDatepickerToggle, MatDatepicker, AsyncPipe, NgrxFormsModule, NgIf],
  templateUrl: './persoon-nieuw-fiche.component.html',
  styleUrl: './persoon-nieuw-fiche.component.scss'
})
export class PersoonNieuwFicheComponent {
  formState$: Observable<FormGroupState<NatuurlijkPersoonFormValue>>;

  constructor(private store: Store<any>) {
    this.formState$ = store.select('genealogie').pipe(map(s => s.nieuwNatuurlijkPersoon));
   // this.formState$ = store.select(nieuwNatuurlijkPersoonForm).pipe(tap(console.log));
  }

  dateValueConverter = NgrxValueConverters.dateToISOString;

  opslaan() {
    this.store.dispatch(maakNieuwNatuurlijkPersoon());
  }
}


