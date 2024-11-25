import {Component, inject, OnInit} from '@angular/core';
import {TIPPY_REF} from "@ngneat/helipopper";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {NgrxFormsModule, NgrxValueConverters} from "ngrx-forms";
import {MatButton} from "@angular/material/button";
import {Store} from "@ngrx/store";
import {PersonenState} from "../../../store/personen.reducer";
import {FormBuilder, FormControl, ReactiveFormsModule, Validators} from "@angular/forms";
import {wijzigRelatie, wijzigPersoonsgegevens} from "../../../store/personen.acties";
import {RelatieUpdateDto} from "../../../../model/relatie-update-dto";
import {getGeladenPeroonFiche} from "../../../store/personen.selector";
import {PersoonsgegevensDto} from "../../../../model/persoonsgegevens-dto";
import {RelatieDto} from "../../../../model/relatie-dto";
import {MatCheckbox} from "@angular/material/checkbox";

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
    MatCheckbox,

  ],
  templateUrl: './update-huwelijk.component.html',
  styleUrl: './update-huwelijk.component.scss'
})
export class UpdateHuwelijkComponent implements OnInit {
  tippy = inject(TIPPY_REF);

  private formBuilder = inject(FormBuilder);
  huwelijksgegevensForm = this.formBuilder.group({
    id: new FormControl<number>(0, [Validators.required]),
    gehuwdOp:  new FormControl<Date | null>(null),
    gehuwdTe: [''],
    uitElkaar: new FormControl<Boolean | null>(false),
  });

  constructor(private store: Store<PersonenState>) {

  }

  ngOnInit() {

    this.tippy.props.arrow = true;
    this.tippy.props.interactive = true;
    this.tippy.props.hideOnClick = false;
    this.tippy.props.zIndex = 1;
    this.tippy.props.theme = 'light'

    let relatie: RelatieDto = this.tippy.data;

    this.huwelijksgegevensForm = this.formBuilder.group({
      id: new FormControl<number>(relatie.id),
      gehuwdOp: new FormControl<Date | null>(relatie.gehuwdOp),
      gehuwdTe: [relatie.gehuwdTe],
      uitElkaar:  new FormControl<Boolean | null>(!!relatie.uitElkaar),

    });

  }



  opslaan() {

    let huwelijk: RelatieUpdateDto = new RelatieUpdateDto(this.tippy.data.id, this.huwelijksgegevensForm.value.gehuwdOp,
      this.huwelijksgegevensForm.value.gehuwdTe, this.huwelijksgegevensForm.value.uitElkaar);
    this.store.dispatch(wijzigRelatie({relatie: huwelijk}));
    this.tippy.hide();
  }

















}
