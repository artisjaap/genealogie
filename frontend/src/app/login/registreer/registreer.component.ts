import { Component } from '@angular/core';
import * as acties from "../store/login.acties";
import {LoginDto, RegistreerGebruikerDto} from "../../model/login-dto";
import {Store} from "@ngrx/store";
import {LoginState} from "../store/login.reducer";

@Component({
  selector: 'app-registreer',
  standalone: true,
  imports: [],
  templateUrl: './registreer.component.html',
  styleUrl: './registreer.component.scss'
})
export class RegistreerComponent {

  constructor(private store: Store<LoginState>) {

  }

  registreer() {
    this.store.dispatch(acties.maakNieuweGebruikerAan({nieuweGebruiker: new RegistreerGebruikerDto("naam", "voornaam", "test@gmail.com", "wachtwoord")}));
  }
}
