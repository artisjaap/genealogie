import {Action, createReducer, createSelector} from "@ngrx/store";
import {createFormGroupState, FormGroupState, onNgrxForms} from "ngrx-forms";


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

export interface GenealogiesState {
  nieuwNatuurlijkPersoon: FormGroupState<NatuurlijkPersoonFormValue>;
}

export const initialState: GenealogiesState = {
  nieuwNatuurlijkPersoon: initialFormState
}

export const createGenealogieReducer = createReducer(
  initialState,
  onNgrxForms(),
);

export function theGenealogieReducer(state: GenealogiesState | undefined, action: Action) {
  return createGenealogieReducer(state, action);
}


export const selectState = (state: any) => state.select('genealogie');

export const nieuwNatuurlijkPersoonForm = createSelector(selectState, state => state.nieuwNatuurlijkPersoon);

