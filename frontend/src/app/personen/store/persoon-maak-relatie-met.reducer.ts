import {Action, createReducer, on} from '@ngrx/store';
import {createFormGroupState, FormGroupState, onNgrxForms} from "ngrx-forms";
import {relatieMetNatuurlijkPersoonAangemaakt} from "./personen.acties";
import {PersonenNieuwFormState} from "./personen-nieuw-persoon-form.reducer";

export const persoonMaakRelatieMetFeatureKey = 'persoonMaakRelatieForm';

export interface PersoonRelatieMetFormValue {
  naam: string;
  voornaam: string;
  geslacht: string;
  geborenOp: string;
  overledenOp: string;
}
const PERSOON_RELATIE_MET_FORM_ID = 'Persoon relatie met Form';
const initialFormState = createFormGroupState<PersoonRelatieMetFormValue>(PERSOON_RELATIE_MET_FORM_ID, {
  naam: '',
  voornaam: '',
  geslacht: 'MAN',
  geborenOp: "",
  overledenOp: "",
});


export interface PersoonRelatieMetFormState {
  nieuwNatuurlijkPersoon: FormGroupState<PersoonRelatieMetFormValue>;

}

export const thePersoonRelatieMetFormInitialState: PersoonRelatieMetFormState = {
  nieuwNatuurlijkPersoon: initialFormState
};


const createThePersoonRelatieMetFormReducer = createReducer(
  thePersoonRelatieMetFormInitialState,
  onNgrxForms(),
  on(relatieMetNatuurlijkPersoonAangemaakt, (state: PersonenNieuwFormState, {relatie}) => ({
    ...state,
    nieuwNatuurlijkPersoon: initialFormState
  })),


);

export function thePersoonRelatieMetFormReducer(state: PersoonRelatieMetFormState | undefined, action: Action) {
  return createThePersoonRelatieMetFormReducer(state, action);
}
