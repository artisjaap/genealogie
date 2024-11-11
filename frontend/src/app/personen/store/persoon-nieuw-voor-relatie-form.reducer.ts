import {Action, createReducer} from '@ngrx/store';
import {createFormGroupState, FormGroupState, onNgrxForms} from "ngrx-forms";
import {PersonenNieuwFormState} from "./personen-nieuw-persoon-form.reducer";

export const personenNieuwVoorRelatieFormFeatureKey = 'personenNieuwVoorRelatieForm';

export interface NatuurlijkPersoonVoorRelatieFormValue {
  naam: string;
  voornaam: string;
  geslacht: string;
  geborenOp: string;
  overledenOp: string;
}
const NATUURLIJK_PERSOON_VOOR_RELATIE_FORM_ID = 'Natuurlijk persoon voor relatie Form';
const initialFormState = createFormGroupState<NatuurlijkPersoonVoorRelatieFormValue>(NATUURLIJK_PERSOON_VOOR_RELATIE_FORM_ID, {
  naam: '',
  voornaam: '',
  geslacht: 'MAN',
  geborenOp: "",
  overledenOp: "",
});


export interface PersonenNieuwVoorRelatieFormState {
  persoonVoorRelatie: FormGroupState<NatuurlijkPersoonVoorRelatieFormValue>;

}

export const thePersonenNieuwVoorRelatieFormInitialState: PersonenNieuwVoorRelatieFormState = {
  persoonVoorRelatie: initialFormState
};


const createThePersonenNieuwVoorRelatieFormReducer = createReducer(
  thePersonenNieuwVoorRelatieFormInitialState,
  onNgrxForms(),


);

export function thePersonenNieuwFormReducer(state: PersonenNieuwVoorRelatieFormState | undefined, action: Action) {
  return createThePersonenNieuwVoorRelatieFormReducer(state, action);
}
