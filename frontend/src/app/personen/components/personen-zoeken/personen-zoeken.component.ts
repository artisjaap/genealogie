import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {MatButtonModule} from "@angular/material/button";
import {Store} from "@ngrx/store";
import {getGevondenPersonen} from "../../store/personen.selector";
import {zoekPersonen} from "../../store/personen.acties";
import {AsyncPipe, DatePipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {faBars} from "@fortawesome/free-solid-svg-icons";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {
  MatCell,
  MatCellDef,
  MatColumnDef,
  MatHeaderCell,
  MatHeaderCellDef,
  MatHeaderRow,
  MatHeaderRowDef,
  MatRow,
  MatRowDef,
  MatTable,
  MatTableDataSource
} from "@angular/material/table";
import {NatuurlijkPersoonDto} from "../../../model/genealogie-dto";


@Component({
    selector: 'app-personen-zoeken',
    imports: [
        RouterLink, MatButtonModule, FaIconComponent, MatTable, MatColumnDef, MatHeaderCell, MatCell, MatHeaderRow, MatRow, MatRowDef, MatHeaderRowDef, MatCellDef, MatHeaderCellDef, DatePipe
    ],
    templateUrl: './personen-zoeken.component.html',
    styleUrl: './personen-zoeken.component.scss'
})
export class PersonenZoekenComponent {
  public datasource = new MatTableDataSource<NatuurlijkPersoonDto>();

  displayedColumns: string[] = ['naam', 'voornaam', 'geborenOp', 'geborenTe', 'overledenOp', 'overledenTe', 'acties'];


  constructor(private store: Store<any>) {
    this.store.select(getGevondenPersonen)
      .subscribe(data => {
        this.datasource.data = data;
      });
  }

  zoeken(zoek: HTMLInputElement) {
    this.store.dispatch(zoekPersonen({zoekString: zoek.value}));
  }

  protected readonly faBars = faBars;
}
