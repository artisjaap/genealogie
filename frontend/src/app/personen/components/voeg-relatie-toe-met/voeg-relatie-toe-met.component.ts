import {Component, inject} from '@angular/core';
import {AsyncPipe, NgIf} from "@angular/common";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {MatButton} from "@angular/material/button";
import {MatDatepicker, MatDatepickerInput, MatDatepickerToggle} from "@angular/material/datepicker";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatRadioButton, MatRadioGroup} from "@angular/material/radio";
import {FormGroupState, NgrxFormsModule, NgrxValueConverters} from "ngrx-forms";

import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {Observable} from "rxjs";
import {NatuurlijkPersoonVoorRelatieFormValue} from "../../store/persoon-nieuw-voor-relatie-form.reducer";
import {Store} from "@ngrx/store";
import {getPersoonVoorRelatieMetForm} from "../../store/personen.selector";
import {maakRelatieMet, sluitVoegRelatieToeMet} from "../../store/personen.acties";
import {faXmark} from "@fortawesome/free-solid-svg-icons";
import {NatuurlijkPersoonDto} from "../../../model/genealogie-dto";

@Component({
    selector: 'app-voeg-relatie-toe-met',
    imports: [
        AsyncPipe,
        FaIconComponent,
        MatButton,
        MatDatepicker,
        MatDatepickerInput,
        MatDatepickerToggle,
        MatFormField,
        MatInput,
        MatLabel,
        MatRadioButton,
        MatRadioGroup,
        MatSuffix,
        NgIf,
        NgrxFormsModule
    ],
    templateUrl: './voeg-relatie-toe-met.component.html',
    styleUrl: './voeg-relatie-toe-met.component.scss'
})
export class VoegRelatieToeMetComponent {
  protected readonly faXmark = faXmark;

  readonly data: {natuurlijkPersoon: NatuurlijkPersoonDto} = inject<{ natuurlijkPersoon: NatuurlijkPersoonDto }>(MAT_DIALOG_DATA);

  formState$: Observable<FormGroupState<NatuurlijkPersoonVoorRelatieFormValue>>;

  constructor(private state: Store<any>) {
    this.formState$ = state.select(getPersoonVoorRelatieMetForm);
  }

  dateValueConverter = NgrxValueConverters.dateToISOString;

  opslaan() {
    this.state.dispatch(maakRelatieMet());
  }

  close() {
    this.state.dispatch(sluitVoegRelatieToeMet());
  }

}
