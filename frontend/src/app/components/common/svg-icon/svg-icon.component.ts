import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-svg-icon',
  imports: [],
  template: `
    <svg>

      <use attr.xlink:href="assets/sprites/sprite.svg#{{icon}}"></use>
    </svg>
  `,
  styleUrl: './svg-icon.component.scss'
})
export class SvgIconComponent {
  @Input() icon: string = "";
}
