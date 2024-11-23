import {Action, createReducer, on} from '@ngrx/store';
import {createFormGroupState, FormGroupState, onNgrxForms} from "ngrx-forms";
import {nieuwNatuurlijkPersoonAangemaakt, oudersVanNatuurlijkPersoonAangemaakt} from "./personen.acties";
import {OudersVoorPersoonFormState} from "./ouders-voor-persoon.reducer";

export const personenNieuwFormFeatureKey = 'personenNieuwForm';

export interface NatuurlijkPersoonFormValue {
  naam: string;
  voornaam: string;
  geslacht: string;
  geborenOp: string;
  overledenOp: string;
}
const NATUURLIJK_PERSOON_FORM_ID = 'Natuurlijk persoon Form';
const initialFormState = createFormGroupState<NatuurlijkPersoonFormValue>(NATUURLIJK_PERSOON_FORM_ID, {
  naam: '',
  voornaam: '',
  geslacht: 'MAN',
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
  on(nieuwNatuurlijkPersoonAangemaakt, (state: PersonenNieuwFormState, {natuurlijkPersoon}) => ({
    ...state,
    nieuwNatuurlijkPersoon: initialFormState
  })),


);

export function thePersonenNieuwFormReducer(state: PersonenNieuwFormState | undefined, action: Action) {
  return createThePersonenNieuwFormReducer(state, action);
}
