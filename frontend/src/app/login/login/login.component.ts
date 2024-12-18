import {Component, inject} from '@angular/core';
import {Store} from "@ngrx/store";
import * as acties from "../store/login.acties";
import {LoginState} from "../store/login.reducer";
import {LoginDto} from "../../model/login-dto";
import {RouterLink} from "@angular/router";
import {FormBuilder, FormControl, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {IconSpriteModule} from "ng-svg-icon-sprite";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    RouterLink,
    ReactiveFormsModule,
    MatDatepicker,
    MatDatepickerInput,
    MatDatepickerToggle,
    MatFormField,
    MatInput,
    MatLabel,
    MatSuffix,
    IconSpriteModule
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
