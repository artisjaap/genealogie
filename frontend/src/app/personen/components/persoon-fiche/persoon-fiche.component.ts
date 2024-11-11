import {Component, inject} from '@angular/core';
import {Store} from "@ngrx/store";
import {PersonenState} from "../../store/personen.reducer";
import {
  getGeladenPeroonFiche,
  getOudersVoorPersoon,
  getOudersVoorPersoonData,
  getPersoonVoorRelatie,
  getPersoonVoorRelatieData,
  getToonDocumenten,
  getToonDocumentenData
} from "../../store/personen.selector";
import {NatuurlijkPersoonFicheDto} from "../../../model/natuurlijk-persoon-fiche-dto";
import {Observable, take} from "rxjs";
import {AsyncPipe, JsonPipe, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {RouterLink} from "@angular/router";
import {UploadDocumentComponent} from "../upload-document/upload-document.component";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {NatuurlijkPersoonDto} from "../../../model/natuurlijk-persoon-dto";
import {RelatieDto} from "../../../model/relatie-dto";
import {DialogData} from "../../../model/document-upload-data-dts";
import {OudersVanPersoonComponent} from "../ouders-van-persoon/ouders-van-persoon.component";
import {PersoonVoorRelatieComponent} from "../persoon-voor-relatie/persoon-voor-relatie.component";
import {toonDocumentPopup, toonOudersVanPersoon, toonPersoonVoorRelatie} from "../../store/personen.acties";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {faDiagramPredecessor, faDiagramSuccessor, faFileText,} from "@fortawesome/free-solid-svg-icons";
import {faStar} from "@fortawesome/free-regular-svg-icons";
import {DocumentPopupComponent} from "../document-popup/document-popup.component";

@Component({
  selector: 'app-persoon-fiche',
  standalone: true,
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    JsonPipe,
    RouterLink,
    FontAwesomeModule,
    NgOptimizedImage
  ],
  templateUrl: './persoon-fiche.component.html',
  styleUrl: './persoon-fiche.component.scss'
})
export class PersoonFicheComponent {
  readonly dialog = inject(MatDialog);


  public persoonFiche$: Observable<NatuurlijkPersoonFicheDto | undefined>;
  private documentDialogRef: MatDialogRef<UploadDocumentComponent, any> | undefined;
  private persoonVoorRelatieDialogRef: MatDialogRef<PersoonVoorRelatieComponent, any> | undefined;
  private oudersVoorPersoonDialogRef: MatDialogRef<OudersVanPersoonComponent, any> | undefined;

  constructor(private store: Store<PersonenState>) {
    this.persoonFiche$ = store.select(getGeladenPeroonFiche);

    store.select(getToonDocumenten).subscribe(toon => {
      if (toon) {
        store.select(getToonDocumentenData).pipe(take(1)).subscribe(data => {
          this.documentDialogRef = this.dialog.open(UploadDocumentComponent, {
            height: '600px',
            width: '1000px',
            disableClose: true,
            data: data
          });
        })
      } else {
        if (this.documentDialogRef) {
          this.documentDialogRef.close();
        }
      }
    });
    store.select(getPersoonVoorRelatie).subscribe(
      toon => {
        if (toon) {
          store.select(getPersoonVoorRelatieData).pipe(take(1)).subscribe(data => {
            this.persoonVoorRelatieDialogRef = this.dialog.open(PersoonVoorRelatieComponent, {
              height: '600px',
              width: '1000px',
              disableClose: true,
              data: data
            });
          })
        } else {
          if (this.persoonVoorRelatieDialogRef) {
            this.persoonVoorRelatieDialogRef.close();
          }
        }
      });
    store.select(getOudersVoorPersoon).subscribe(
      toon => {
        if (toon) {
          store.select(getOudersVoorPersoonData).pipe(take(1)).subscribe(data => {
            this.oudersVoorPersoonDialogRef = this.dialog.open(OudersVanPersoonComponent, {
              height: '600px',
              width: '600px',
              disableClose: true,
              data: data
            });
          })
        } else {
          if (this.oudersVoorPersoonDialogRef) {
            this.oudersVoorPersoonDialogRef.close();
          }
        }
      });

  }

  documentToevoegen(natuurlijkPersoonDto: NatuurlijkPersoonDto | null, relatieDto: RelatieDto | null) {
    let dialogData = new DialogData(natuurlijkPersoonDto, relatieDto);
    this.store.dispatch(toonDocumentPopup({dialogData}));
  }

  oudersToevoegen(natuurlijkPersoonDto: NatuurlijkPersoonDto | null, relatieDto: RelatieDto | null) {
    let dialogData = new DialogData(natuurlijkPersoonDto, relatieDto);
    this.store.dispatch(toonOudersVanPersoon({dialogData}));
  }

  persoonAanRelatieToevoegen(natuurlijkPersoonDto: NatuurlijkPersoonDto | null, relatieDto: RelatieDto | null) {
    let dialogData = new DialogData(natuurlijkPersoonDto, relatieDto);
    this.store.dispatch(toonPersoonVoorRelatie({dialogData}));
  }


  protected readonly faFileText = faFileText;
  protected readonly faDiagramPredecessor = faDiagramPredecessor;
  protected readonly faDiagramSuccessor = faDiagramSuccessor;

  protected readonly faStar = faStar;

  toonImage(img: HTMLImageElement) {
    this.dialog.open(DocumentPopupComponent, {
      height: "calc(100% - 30px)",
      width: "calc(100% - 30px)",
      maxWidth: "100%",
      maxHeight: "100%",
      disableClose: true,
      data: img
    });
  }
}

