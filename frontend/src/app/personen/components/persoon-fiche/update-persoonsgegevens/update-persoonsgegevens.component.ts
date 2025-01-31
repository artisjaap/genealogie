import {Component, inject} from '@angular/core';
import {MatButton} from "@angular/material/button";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormBuilder, FormControl, ReactiveFormsModule, Validators} from "@angular/forms";
import {TIPPY_REF} from "@ngneat/helipopper";
import {Store} from "@ngrx/store";
import {PersonenState} from "../../../store/personen.reducer";
import {getGeladenPeroonFiche} from "../../../store/personen.selector";
import {wijzigPersoonsgegevens} from "../../../store/personen.acties";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {PersoonsgegevensDto} from "../../../../model/genealogie-dto";

@Component({
    selector: 'app-update-persoonsgegevens',
    imports: [
        MatButton,
        MatDatepicker,
        MatDatepickerInput,
        MatDatepickerToggle,
        MatFormField,
        MatInput,
        MatLabel,
        MatSuffix,
        ReactiveFormsModule,
        MatRadioButton,
        MatRadioGroup
    ],
    templateUrl: './update-persoonsgegevens.component.html',
    styleUrl: './update-persoonsgegevens.component.scss'
})
export class UpdatePersoonsgegevensComponent {
  tippy = inject(TIPPY_REF);

  private formBuilder = inject(FormBuilder);
  persoonsgegevensForm = this.formBuilder.group({
    id: new FormControl<number>(0, [Validators.required]),
    naam: [''],
    voornaam: [''],
    geslacht: [''],
    geborenOp: new FormControl<Date | null>(null),
    geborenTe: [''],
    overledenOp: new FormControl<Date | null>(null),
    overledenTe: [''],
  });


  constructor(private store: Store<PersonenState>) {
    this.store.select(getGeladenPeroonFiche).subscribe(
      fiche => {
        this.persoonsgegevensForm = this.formBuilder.group({
          id: new FormControl<number>(fiche ? fiche.natuurlijkPersoon.id : 0, [Validators.required]),
          naam: [fiche ? fiche.natuurlijkPersoon.naam : ''],
          voornaam: [fiche ? fiche.natuurlijkPersoon.voornaam : ''],
          geslacht: [fiche ? fiche.natuurlijkPersoon.geslacht : ''],
          geborenOp: new FormControl<Date | null>(fiche ? fiche.natuurlijkPersoon.geborenOp : null),
          geborenTe: [fiche ? fiche.natuurlijkPersoon.geborenTe : ''],
          overledenOp: new FormControl<Date | null>(fiche ? fiche.natuurlijkPersoon.overledenOp : null),
          overledenTe: [fiche ? fiche.natuurlijkPersoon.overledenTe : ''],
        });
      }
    )

  }

  ngOnInit() {

    this.tippy.props.arrow = true;
    this.tippy.props.interactive = true;
    this.tippy.props.hideOnClick = false;
    this.tippy.props.zIndex = 1;
    this.tippy.props.theme = 'light';
    this.tippy.props.maxWidth = '600px';


  }

  opslaan() {
    console.log(this.persoonsgegevensForm.value);
    let persoonsgegevens: PersoonsgegevensDto = {
      id: this.persoonsgegevensForm.value.id,
      naam: this.persoonsgegevensForm.value.naam,
      voornaam: this.persoonsgegevensForm.value.voornaam,
      geslacht: this.persoonsgegevensForm.value.geslacht,
      geborenOp: this.persoonsgegevensForm.value.geborenOp,
      geborenTe: this.persoonsgegevensForm.value.geborenTe,
      overledenOp: this.persoonsgegevensForm.value.overledenOp,
      overledenTe: this.persoonsgegevensForm.value.overledenTe,
    }
    this.store.dispatch(wijzigPersoonsgegevens({persoonsgegevens}));
    this.tippy.hide();
  }

  close() {
    this.tippy.hide();
  }
}
