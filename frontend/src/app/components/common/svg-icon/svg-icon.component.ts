import {Component, Input} from '@angular/core';
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-svg-icon',
  imports: [NgIf],
  template: `
    <svg *ngIf="!!icon" [attr.width]="width" [attr.height]="width" preserveAspectRatio="xMidYMid meet">
      <use [attr.xlink:href]="'assets/sprites/sprite.svg#' + icon"></use>
    </svg>
  `,
  styleUrl: './svg-icon.component.scss'
})
export class SvgIconComponent {
  @Input() icon: string | null= "";
  @Input() width: string | number = 18; // e.g. 18, '18', '18px', '1.5rem'
}
