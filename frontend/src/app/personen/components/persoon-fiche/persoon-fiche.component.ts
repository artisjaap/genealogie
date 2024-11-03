import { Component } from '@angular/core';
import {Store} from "@ngrx/store";
import {PersonenState} from "../../store/personen.reducer";
import {getGeladenPeroonFiche} from "../../store/personen.selector";
import {NatuurlijkPersoonFicheDto} from "../../../model/natuurlijk-persoon-fiche-dto";
import {Observable} from "rxjs";
import {AsyncPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-persoon-fiche',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    JsonPipe,
    RouterLink
  ],
  templateUrl: './persoon-fiche.component.html',
  styleUrl: './persoon-fiche.component.scss'
})
export class PersoonFicheComponent {

  public persoonFiche$: Observable<NatuurlijkPersoonFicheDto | undefined> ;

  constructor(private store: Store<PersonenState>) {
    this.persoonFiche$ = store.select(getGeladenPeroonFiche);

  }

}
