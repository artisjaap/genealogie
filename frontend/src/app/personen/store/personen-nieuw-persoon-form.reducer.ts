import {Action, createReducer} from '@ngrx/store';
import {createFormGroupState, FormGroupState, onNgrxForms} from "ngrx-forms";

export const personenNieuwFormFeatureKey = 'personenNieuwForm';

export interface NatuurlijkPersoonFormValue {
  naam: string;
  voornaam: string;
  geborenOp: string;
  overledenOp: string;
}
const NATUURLIJK_PERSOON_FORM_ID = 'Natuurlijk persoon Form';
const initialFormState = createFormGroupState<NatuurlijkPersoonFormValue>(NATUURLIJK_PERSOON_FORM_ID, {
  naam: '',
  voornaam: '',
  geborenOp: "",
  overledenOp: "",
});


export interface PersonenNieuwFormState {
  nieuwNatuurlijkPersoon: FormGroupState<NatuurlijkPersoonFormValue>;

}

export const thePersonenNieuwFormInitialState: PersonenNieuwFormState = {
  nieuwNatuurlijkPersoon: initialFormState
};


const createThePersonenNieuwFormReducer = createReducer(
  thePersonenNieuwFormInitialState,
  onNgrxForms(),


);

export function thePersonenNieuwFormReducer(state: PersonenNieuwFormState | undefined, action: Action) {
  return createThePersonenNieuwFormReducer(state, action);
}
