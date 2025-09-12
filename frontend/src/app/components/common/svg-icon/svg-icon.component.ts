import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-svg-icon',
  imports: [],
  template: `
    <svg [attr.width]="width" [attr.height]="width" preserveAspectRatio="xMidYMid meet">
      <use [attr.xlink:href]="'assets/sprites/sprite.svg#' + icon"></use>
    </svg>
  `,
  styleUrl: './svg-icon.component.scss'
})
export class SvgIconComponent {
  @Input() icon: string = "";
  @Input() width: string | number = 18; // e.g. 18, '18', '18px', '1.5rem'
}
