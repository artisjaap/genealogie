import {Component, inject} from '@angular/core';
import * as acties from "../store/login.acties";
import {LoginDto, RegistreerGebruikerDto} from "../../model/login-dto";
import {Store} from "@ngrx/store";
import {LoginState} from "../store/login.reducer";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {FormBuilder, FormControl, ReactiveFormsModule, Validators} from "@angular/forms";

@Component({
  selector: 'app-registreer',
  standalone: true,
    imports: [
        MatFormField,
        MatInput,
        MatLabel,
        ReactiveFormsModule
    ],
  templateUrl: './registreer.component.html',
  styleUrl: './registreer.component.scss'
})
export class RegistreerComponent {

  private formBuilder = inject(FormBuilder);

  registreerForm = this.formBuilder.group({
    email: new FormControl<string>("", [Validators.required]),
    wachtwoord: new FormControl<string>("", [Validators.required]),
    naam: new FormControl<string>("", [Validators.required]),
    voornaam: new FormControl<string>("", [Validators.required]),
  });

  constructor(private store: Store<LoginState>) {

  }

  registreer() {
    this.store.dispatch(acties.maakNieuweGebruikerAan({nieuweGebruiker: new RegistreerGebruikerDto(
      this.registreerForm.value.naam || '',
        this.registreerForm.value.voornaam || '',
        this.registreerForm.value.email || '',
        this.registreerForm.value.wachtwoord || '')}));
  }
}
