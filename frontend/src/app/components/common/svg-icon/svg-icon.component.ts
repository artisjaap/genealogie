import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-svg-icon',
  imports: [],
  template: `
    <svg>
      // SVG elements don't have properties, therefore attribute binding is needed
      // https://stackoverflow.com/a/35082700
      <use attr.xlink:href="assets/sprites/sprite.svg#{{icon}}"></use>
    </svg>
  `,
  styleUrl: './svg-icon.component.scss'
})
export class SvgIconComponent {
  @Input() icon: string = "";
}
