import {Component, ElementRef, inject, ViewChild} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
    selector: 'app-document-popup',
    imports: [],
    templateUrl: './document-popup.component.html',
    styleUrl: './document-popup.component.scss'
})
export class DocumentPopupComponent {

  readonly data: HTMLImageElement = inject<HTMLImageElement>(MAT_DIALOG_DATA);

  @ViewChild('imgTarget') imgTarget:ElementRef|null=null;

  ngAfterViewInit() {
    if(this.imgTarget){
      this.imgTarget.nativeElement.appendChild(this.data);

    }
  }

}
