import {Component, inject, OnDestroy} from '@angular/core';
import {Store} from "@ngrx/store";
import {PersonenState} from "../../store/personen.reducer";
import {
  getGeladenPeroonFiche,
  getOudersVoorPersoon,
  getOudersVoorPersoonData,
  getPersoonVoorRelatie,
  getPersoonVoorRelatieData,
  getToonDocumenten,
  getToonDocumentenData,
  getVoegRelatieToeMet
} from "../../store/personen.selector";
import {Observable, of, switchMap, take, tap, finalize} from "rxjs";
import {AsyncPipe, DatePipe, JsonPipe, NgClass, NgForOf, NgIf, NgOptimizedImage} from "@angular/common";
import {RouterLink} from "@angular/router";
import {UploadDocumentComponent} from "../upload-document/upload-document.component";
import {MatDialog, MatDialogRef} from "@angular/material/dialog";
import {OudersVanPersoonComponent} from "../ouders-van-persoon/ouders-van-persoon.component";
import {PersoonVoorRelatieComponent} from "../persoon-voor-relatie/persoon-voor-relatie.component";
import {
  toonDocumentPopup,
  toonOudersVanPersoon,
  toonPersoonVoorRelatie,
  toonVoegRelatieToeMet
} from "../../store/personen.acties";
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {
  faDiagramPredecessor,
  faDiagramSuccessor,
  faEdit,
  faFileText,
  faMars,
  faMultiply,
  faPerson,
  faRing,
  faVenus,
  faLink
} from "@fortawesome/free-solid-svg-icons";
import {faStar} from "@fortawesome/free-regular-svg-icons";
import {DocumentPopupComponent} from "../document-popup/document-popup.component";
import {VoegRelatieToeMetComponent} from "../voeg-relatie-toe-met/voeg-relatie-toe-met.component";
import {TippyDirective} from "@ngneat/helipopper";
import {UpdateHuwelijkComponent} from "./update-huwelijk/update-huwelijk.component";
import {FamilieTreeComponent} from "../familie-tree/familie-tree.component";
import {UpdatePersoonsgegevensComponent} from "./update-persoonsgegevens/update-persoonsgegevens.component";
import {PersoonReferenceComponent} from "../persoon-reference/persoon-reference.component";
import {DialogData, NatuurlijkPersoonDto, NatuurlijkPersoonFicheDto, RelatieDto} from "../../../model/genealogie-dto";
import {SvgIconComponent} from "../../../components/common/svg-icon/svg-icon.component";
import {DocumentUtility} from "../../../service/document-utility";

@Component({
    selector: 'app-persoon-fiche',
  imports: [
    AsyncPipe,
    NgIf,
    NgForOf,
    RouterLink,
    FontAwesomeModule,
    TippyDirective,
    FamilieTreeComponent,
    NgClass,
    DatePipe,
    PersoonReferenceComponent,
    SvgIconComponent
  ],
  providers: [DocumentUtility],
    templateUrl: './persoon-fiche.component.html',
    styleUrl: './persoon-fiche.component.scss'
})
export class PersoonFicheComponent implements OnDestroy {
  readonly dialog = inject(MatDialog);

  public persoonFiche$: Observable<NatuurlijkPersoonFicheDto | undefined>;
  private documentDialogRef: MatDialogRef<UploadDocumentComponent, any> | undefined;
  private persoonVoegRelatieToeMetDialogRef: MatDialogRef<VoegRelatieToeMetComponent, any> | undefined;
  private persoonVoorRelatieDialogRef: MatDialogRef<PersoonVoorRelatieComponent, any> | undefined;
  private oudersVoorPersoonDialogRef: MatDialogRef<OudersVanPersoonComponent, any> | undefined;

  constructor(private store: Store<PersonenState>, public documentUtility: DocumentUtility) {
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

    store.select(getVoegRelatieToeMet).subscribe(
      toon => {
        if (toon) {
          store.select(getVoegRelatieToeMet).pipe(take(1)).subscribe(data => {
            this.persoonVoegRelatieToeMetDialogRef = this.dialog.open(VoegRelatieToeMetComponent, {
              height: '600px',
              width: '1000px',
              disableClose: true,
              data: data
            });
          })
        } else {
          if (this.persoonVoegRelatieToeMetDialogRef) {
            this.persoonVoegRelatieToeMetDialogRef.close();
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

  voegRelatieToeMet(natuurlijkPersoonDto: NatuurlijkPersoonDto | null) {
    let dialogData = new DialogData(natuurlijkPersoonDto, null);
    this.store.dispatch(toonVoegRelatieToeMet({dialogData}));
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

  protected readonly faPerson = faPerson;
  protected readonly faEdit = faEdit;
  protected readonly UpdateHuwelijkComponent = UpdateHuwelijkComponent;
  protected readonly UpdatePersoonsgegevensComponent = UpdatePersoonsgegevensComponent;
  protected readonly faVenus = faVenus;
  protected readonly faMars = faMars;
  protected readonly faRing = faRing;
  protected readonly faMultiply = faMultiply;
  protected readonly faLink = faLink;

  getDocumentUrl(id: number): string | null {
    return this.documentUtility.getDocumentUrl(id);
  }

  ngOnDestroy(): void {
    this.documentUtility.destroy();
  }

}

