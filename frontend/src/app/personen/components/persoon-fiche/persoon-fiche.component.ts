import {Component, inject} from '@angular/core';
import {Store} from "@ngrx/store";
import {PersonenState} from "../../store/personen.reducer";
import {getGeladenPeroonFiche} from "../../store/personen.selector";
import {NatuurlijkPersoonFicheDto} from "../../../model/natuurlijk-persoon-fiche-dto";
import {Observable} from "rxjs";
import {AsyncPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {RouterLink} from "@angular/router";
import {UploadDocumentComponent} from "../upload-document/upload-document.component";
import {MatDialog} from "@angular/material/dialog";

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
  readonly dialog = inject(MatDialog);


  public persoonFiche$: Observable<NatuurlijkPersoonFicheDto | undefined> ;

  constructor(private store: Store<PersonenState>) {
    this.persoonFiche$ = store.select(getGeladenPeroonFiche);

  }

  documentToevoegen() {
    let dialogRef = this.dialog.open(UploadDocumentComponent, {
      height: '400px',
      width: '600px',
    });
  }
}
