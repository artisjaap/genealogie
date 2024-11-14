import {Component, inject} from '@angular/core';
import {AsyncPipe, NgIf} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatFormFieldModule, MatSuffix} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {FormGroupState, NgrxFormsModule, NgrxValueConverters} from "ngrx-forms";
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {getPersoonVoorRelatieForm} from "../../store/personen.selector";
import {maakNatuurlijkPersoonVoorRelatie, sluitPersoonVoorRelatie} from "../../store/personen.acties";
import {NatuurlijkPersoonVoorRelatieFormValue} from "../../store/persoon-nieuw-voor-relatie-form.reducer";
import {provideNativeDateAdapter} from "@angular/material/core";
import {DialogData} from "../../../model/document-upload-data-dts";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {MatRadioModule} from "@angular/material/radio";
import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";

@Component({
  selector: 'app-persoon-voor-relatie',
  standalone: true,
  providers: [],

  imports: [
    AsyncPipe,
    MatButtonModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,

    MatSuffix,
    NgIf,
    NgrxFormsModule,
    MatRadioModule,
    FaIconComponent
  ],
  templateUrl: './persoon-voor-relatie.component.html',
  styleUrl: './persoon-voor-relatie.component.scss'
})
export class PersoonVoorRelatieComponent {
  protected readonly faXmark = faXmark;

  readonly data: DialogData = inject<DialogData>(MAT_DIALOG_DATA);

  formState$: Observable<FormGroupState<NatuurlijkPersoonVoorRelatieFormValue>>;

  constructor(private state: Store<any>) {
    this.formState$ = state.select(getPersoonVoorRelatieForm);
  }

  dateValueConverter = NgrxValueConverters.dateToISOString;

  opslaan() {
    this.state.dispatch(maakNatuurlijkPersoonVoorRelatie({relatie: this.data.relatie}));
  }

  close() {
    this.state.dispatch(sluitPersoonVoorRelatie());
  }


}
