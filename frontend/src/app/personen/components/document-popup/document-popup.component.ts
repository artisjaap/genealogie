import {Component, ElementRef, inject, ViewChild} from '@angular/core';
import {DialogData} from "../../../model/document-upload-data-dts";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-document-popup',
  standalone: true,
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
