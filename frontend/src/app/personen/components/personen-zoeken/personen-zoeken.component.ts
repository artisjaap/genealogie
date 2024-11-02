import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";
import {MatButtonModule} from "@angular/material/button";
import {Store} from "@ngrx/store";
import {getGevondenPersonen, getPersonenNieuwForm} from "../../store/personen.selector";
import {maakNieuwNatuurlijkPersoon, zoekPersonen} from "../../store/personen.acties";
import {async, Observable} from "rxjs";
import {NatuurlijkPersoonDto} from "../../../model/natuurlijk-persoon-dto";
import {AsyncPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-personen-zoeken',
  standalone: true,
  imports: [
    RouterLink, MatButtonModule, AsyncPipe, NgIf, JsonPipe, NgForOf
  ],
  templateUrl: './personen-zoeken.component.html',
  styleUrl: './personen-zoeken.component.scss'
})
export class PersonenZoekenComponent {
  public gevondenPersonen$: Observable<NatuurlijkPersoonDto[]>;

  constructor(private store: Store<any>) {
     this.gevondenPersonen$ = this.store.select(getGevondenPersonen);
  }

  zoeken(zoek: HTMLInputElement) {
    this.store.dispatch(zoekPersonen({zoekString : zoek.value}));
  }

  protected readonly async = async;
}
