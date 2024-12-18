import { Component } from '@angular/core';
import {Store} from "@ngrx/store";
import {LoginState} from "../../store/login.reducer";
import * as LoginSelector from "../../store/login.selector";
import {getIngelogdeGebruiker} from "../../store/login.selector";
import {Observable} from "rxjs";
import {LoginUserResponseDto} from "../../../model/login-dto";
import {AsyncPipe, NgIf} from "@angular/common";

@Component({
  selector: 'app-login-widget',
  standalone: true,
  imports: [
    NgIf,
    AsyncPipe
  ],
  templateUrl: './login-widget.component.html',
  styleUrl: './login-widget.component.scss'
})
export class LoginWidgetComponent {
  ingelogdeGebruiker$: Observable<LoginUserResponseDto|undefined>;
  constructor(private store: Store<LoginState>) {
    this.ingelogdeGebruiker$ = store.select(LoginSelector.getIngelogdeGebruiker);

  }
}
