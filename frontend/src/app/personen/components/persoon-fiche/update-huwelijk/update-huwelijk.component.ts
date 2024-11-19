import {Component, inject, OnInit} from '@angular/core';
import {TIPPY_REF, TippyInstance } from "@ngneat/helipopper";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {NgrxFormsModule, NgrxValueConverters} from "ngrx-forms";
import {MatButton} from "@angular/material/button";
import {Store} from "@ngrx/store";
import {PersonenState} from "../../../store/personen.reducer";
import {FormControl, ReactiveFormsModule} from "@angular/forms";
import {wijzigHuwelijk} from "../../../store/personen.acties";
import {HuwelijkDto} from "../../../../model/huwelijk-dto";

@Component({
  selector: 'app-update-huwelijk',
  standalone: true,
  imports: [
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatFormField,
    MatInput,
    MatLabel,
    MatSuffix,
    NgrxFormsModule,
    MatButton,
    ReactiveFormsModule,

  ],
  templateUrl: './update-huwelijk.component.html',
  styleUrl: './update-huwelijk.component.scss'
})
export class UpdateHuwelijkComponent implements OnInit {
  tippy = inject(TIPPY_REF);

  readonly date = new FormControl(null);

  constructor(private store: Store<PersonenState>) {

  }

  dateValueConverter = NgrxValueConverters.dateToISOString;


  ngOnInit() {

    this.tippy.props.arrow = true;
    this.tippy.props.interactive = true;
    this.tippy.props.hideOnClick = false;
    this.tippy.props.zIndex = 1;
    this.tippy.props.theme = 'light'

  }

  opslaan(huwelijkTe: HTMLInputElement) {

    let huwelijk: HuwelijkDto = new HuwelijkDto(this.tippy.data.id, this.date.value, huwelijkTe.value);
    this.store.dispatch(wijzigHuwelijk({huwelijk}));
    this.tippy.hide();
  }
}
