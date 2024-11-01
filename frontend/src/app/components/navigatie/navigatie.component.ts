import { Component } from '@angular/core';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-navigatie',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './navigatie.component.html',
  styleUrl: './navigatie.component.scss'
})
export class NavigatieComponent {

}
