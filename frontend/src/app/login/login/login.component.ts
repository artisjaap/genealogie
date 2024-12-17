import { Component } from '@angular/core';
import {Store} from "@ngrx/store";
import * as acties from "../store/login.acties";
import {LoginState} from "../store/login.reducer";
import {LoginDto} from "../../model/login-dto";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  constructor(private store: Store<LoginState>) {

  }

  login() {
    this.store.dispatch(acties.loginVoorGebruiker({login: new LoginDto("test@gmail.com", "wachtwoord")}));
  }
}
