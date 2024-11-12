import {Component} from '@angular/core';
import {AsyncPipe, NgIf} from "@angular/common";
import {MatButton} from "@angular/material/button";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormGroupState, NgrxFormsModule, NgrxValueConverters} from "ngrx-forms";
import {Store} from "@ngrx/store";
import {getOudersVoorPersoonForm} from "../../store/personen.selector";
import {Observable} from "rxjs";
import {maakOudersVanNatuurlijkPersoon, sluitOudersVanPersoon} from "../../store/personen.acties";
import {OudersVoorPersoonFormValue} from "../../store/ouders-voor-persoon.reducer";
import {provideNativeDateAdapter} from "@angular/material/core";
import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'app-ouders-van-persoon',
  standalone: true,
  providers: [provideNativeDateAdapter()],

  imports: [
    AsyncPipe,
    MatButton,
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatFormField,
    MatInput,
    MatLabel,
    MatSuffix,
    NgIf,
    NgrxFormsModule,
    FaIconComponent,


  ],
  templateUrl: './ouders-van-persoon.component.html',
  styleUrl: './ouders-van-persoon.component.scss'
})
export class OudersVanPersoonComponent {
  protected readonly faXmark = faXmark;

  formState$: Observable<FormGroupState<OudersVoorPersoonFormValue>>;

  constructor(private state: Store<any>) {
    this.formState$ = state.select(getOudersVoorPersoonForm);
  }

  dateValueConverter = NgrxValueConverters.dateToISOString;

  opslaan() {
    this.state.dispatch(maakOudersVanNatuurlijkPersoon());
  }

  close() {
    this.state.dispatch(sluitOudersVanPersoon());
  }

}
