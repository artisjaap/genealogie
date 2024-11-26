import {Component} from '@angular/core';
import {RouterLink} from "@angular/router";
import {MatButtonModule} from "@angular/material/button";
import {Store} from "@ngrx/store";
import {getGevondenPersonen, getPersonenNieuwForm} from "../../store/personen.selector";
import {maakNieuwNatuurlijkPersoon, zoekPersonen} from "../../store/personen.acties";
import {async, first, Observable, tap} from "rxjs";
import {NatuurlijkPersoonDto} from "../../../model/natuurlijk-persoon-dto";
import {AsyncPipe, DatePipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {faBars, faCircleInfo, faInfo, faXmark} from "@fortawesome/free-solid-svg-icons";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {
  MatCell, MatCellDef,
  MatColumnDef,
  MatHeaderCell, MatHeaderCellDef,
  MatHeaderRow, MatHeaderRowDef, MatRow, MatRowDef,
  MatTable,
  MatTableDataSource
} from "@angular/material/table";


@Component({
  selector: 'app-personen-zoeken',
  standalone: true,
  imports: [
    RouterLink, MatButtonModule, AsyncPipe, NgIf, JsonPipe, NgForOf, FaIconComponent, MatTable, MatColumnDef, MatHeaderCell, MatCell, MatHeaderRow, MatRow, MatRowDef, MatHeaderRowDef, MatCellDef, MatHeaderCellDef, DatePipe
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
