import {Component, inject} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AsyncPipe, JsonPipe, NgIf, NgSwitch, NgSwitchCase, NgSwitchDefault} from "@angular/common";
import {Observable, throwError} from "rxjs";
import {MatFormField, MatFormFieldModule} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";
import {PersonenState} from "../../store/personen.reducer";
import {Store} from "@ngrx/store";
import {getGeladenDocumentTypes} from "../../store/personen.selector";
import {NgrxFormsModule} from "ngrx-forms";
import {
  MAT_DIALOG_DATA,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogTitle
} from "@angular/material/dialog";
import {MatInputModule} from "@angular/material/input";
import {FormsModule} from "@angular/forms";
import {MatButtonModule} from "@angular/material/button";
import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {documentOpgeladen, sluitDocumentPopup} from "../../store/personen.acties";
import {DialogData, DocumentTypeDto} from "../../../model/genealogie-dto";


@Component({
    selector: 'app-upload-document',
    imports: [
        MatFormField,
        MatSelect,
        MatOption,
        AsyncPipe,
        NgrxFormsModule,
        MatFormFieldModule,
        MatInputModule,
        FormsModule,
        MatButtonModule,

      FaIconComponent,
    ],
    templateUrl: './upload-document.component.html',
    styleUrl: './upload-document.component.scss'
})
export class UploadDocumentComponent {
  protected readonly faXmark = faXmark;

  readonly data: DialogData = inject<DialogData>(MAT_DIALOG_DATA);

  status: "initial" | "uploading" | "success" | "fail" = "initial"; // Variable to store file status
  file: File | null = null; // Variable to store file
  protected documentTypes$: Observable<DocumentTypeDto[]>;

  constructor(private http: HttpClient, private state: Store<PersonenState>) {
    this.documentTypes$ = this.state.select(getGeladenDocumentTypes);

  }

  ngOnInit(): void {
  }

  // On file Select
  onChange(event: any) {
    const file: File = event.target.files[0];

    if (file) {
      this.status = "initial";
      this.file = file;
    }
  }

  onUpload(documentTypeId: string, transcript: string) {
    if (this.file) {
      const formData = new FormData();
      let relatieId: string = this.data.relatie ? this.data.relatie.id.toString() : '';
      let natuurlijkPersoonId: string = this.data.natuurlijkPersoon ? this.data.natuurlijkPersoon.id.toString() : '';

      formData.append('file', this.file, this.file.name);
      formData.append('transcript', transcript);
      formData.append('documentTypeId', documentTypeId);
      formData.append('relatieId', relatieId);
      formData.append('natuurlijkPersoonId', natuurlijkPersoonId);

      const upload$ = this.http.post("/api/document", formData);

      this.status = 'uploading';

      upload$.subscribe({
        next: () => {
          this.status = 'success';
          this.state.dispatch(documentOpgeladen());
        },
        error: (error: any) => {
          this.status = 'fail';
          return throwError(() => error);
        },
      });
    }
  }

  close() {
    this.state.dispatch(sluitDocumentPopup());
  }

}
