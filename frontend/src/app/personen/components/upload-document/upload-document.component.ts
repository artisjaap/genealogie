import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AsyncPipe, NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault} from "@angular/common";
import {Observable, throwError} from "rxjs";
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {PersonenState} from "../../store/personen.reducer";
import {Store} from "@ngrx/store";
import {getGeladenDocumentTypes} from "../../store/personen.selector";
import {DocumentTypeDto} from "../../../model/document-type-dto";
import {NgrxFormsModule} from "ngrx-forms";

@Component({
  selector: 'app-upload-document',
  standalone: true,
  imports: [
    NgSwitch,
    NgSwitchCase,
    NgIf,
    NgSwitchDefault,
    MatFormField,
    MatSelect,
    MatOption,
    AsyncPipe,
    NgrxFormsModule,
    MatFormFieldModule
  ],
  templateUrl: './upload-document.component.html',
  styleUrl: './upload-document.component.scss'
})
export class UploadDocumentComponent {
  status: "initial" | "uploading" | "success" | "fail" = "initial"; // Variable to store file status
  file: File | null = null; // Variable to store file
  protected documentTypes$: Observable<DocumentTypeDto[]>;

  constructor(private http: HttpClient, private state:Store<PersonenState>) {
    this.documentTypes$ = this.state.select(getGeladenDocumentTypes);

  }

  ngOnInit(): void {}

  // On file Select
  onChange(event: any) {
    const file: File = event.target.files[0];

    if (file) {
      this.status = "initial";
      this.file = file;
    }
  }

  onUpload() {
    if (this.file) {
      const formData = new FormData();

      formData.append('file', this.file, this.file.name);
      formData.append('type', "een type");
      formData.append('ref', "een ref");

      const upload$ = this.http.post("/api/document", formData);

      this.status = 'uploading';

      upload$.subscribe({
        next: () => {
          this.status = 'success';
        },
        error: (error: any) => {
          this.status = 'fail';
          return throwError(() => error);
        },
      });
    }
  }
}
