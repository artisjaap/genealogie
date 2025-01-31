import {Component, inject} from '@angular/core';
import {Store} from "@ngrx/store";
import * as acties from "../store/login.acties";
import {LoginState} from "../store/login.reducer";
import {LoginDto} from "../../model/login-dto";
import {RouterLink} from "@angular/router";
import {FormBuilder, FormControl, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";

@Component({
  selector: 'app-login',
  imports: [
    RouterLink,
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatLabel,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  private formBuilder = inject(FormBuilder);

  loginForm = this.formBuilder.group({
    login: new FormControl<string>("", [Validators.required]),
    wachtwoord: new FormControl<string>("", [Validators.required]),
  });

  constructor(private store: Store<LoginState>) {

  }

  login() {
    this.store.dispatch(acties.loginVoorGebruiker({login: new LoginDto(this.loginForm.value.login || '', this.loginForm.value.wachtwoord || '')}));
  }
}
