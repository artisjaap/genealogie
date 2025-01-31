import {Component, Input} from '@angular/core';
import {RouterLink} from "@angular/router";
import {faCross, faMars, faVenus} from "@fortawesome/free-solid-svg-icons";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {NgClass, NgIf} from "@angular/common";
import {NatuurlijkPersoonDto} from "../../../model/genealogie-dto";

@Component({
    selector: 'app-persoon-reference',
    imports: [
        RouterLink,
        NgIf,
        NgClass,
        FaIconComponent,
    ],
    templateUrl: './persoon-reference.component.html',
    styleUrl: './persoon-reference.component.scss'
})
export class PersoonReferenceComponent {
  @Input() natuurlijkPersoon!: NatuurlijkPersoonDto | null;
  @Input() toonGeslacht: Boolean = true;
  @Input() highlight: Boolean = false;

  protected readonly faMars = faMars;
  protected readonly faVenus = faVenus;
  protected readonly faCross = faCross;
}
