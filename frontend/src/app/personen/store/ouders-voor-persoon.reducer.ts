import {Action, createReducer, on} from '@ngrx/store';
import {createFormGroupState, FormGroupState, onNgrxForms, updateGroup} from "ngrx-forms";
import {PersonenNieuwFormState} from "./personen-nieuw-persoon-form.reducer";
import {nakomelingenVanGeladen, oudersVanNatuurlijkPersoonAangemaakt} from "./personen.acties";
import {PersoonDataState} from "./personen-persoon.reducer";

export const oudersVoorPersoonFormFeatureKey = 'oudersVoorPersoonForm';

export interface OudersVoorPersoonFormValue {
  persoon1naam: string;
  persoon1voornaam: string;
  persoon1geborenOp: string;
  persoon1overledenOp: string;
  persoon2naam: string;
  persoon2voornaam: string;
  persoon2geborenOp: string;
  persoon2overledenOp: string;
}
const OUDERS_VOOR_PERSOON_FORM_ID = 'Ouders voor persoon Form';

const initialFormState = createFormGroupState<OudersVoorPersoonFormValue>(OUDERS_VOOR_PERSOON_FORM_ID, {
  persoon1naam: '',
  persoon1voornaam: '',
  persoon1geborenOp: "",
  persoon1overledenOp: "",
  persoon2naam: '',
  persoon2voornaam: '',
  persoon2geborenOp: "",
  persoon2overledenOp: "",
});


export interface OudersVoorPersoonFormState {
  oudersVoorPersoon: FormGroupState<OudersVoorPersoonFormValue>;

}

export const theOudersVoorPersoonFormInitialState: OudersVoorPersoonFormState = {
  oudersVoorPersoon: initialFormState
};


const createOudersVoorPersoonFormReducer = createReducer(
  theOudersVoorPersoonFormInitialState,
  onNgrxForms(),
  on(oudersVanNatuurlijkPersoonAangemaakt, (state: OudersVoorPersoonFormState, {genealogischDriehoekje}) => ({
    ...state,
    oudersVoorPersoon: initialFormState
  })),



);

export function thOudersVoorPersoonFormReducer(state: OudersVoorPersoonFormState | undefined, action: Action) {
  return createOudersVoorPersoonFormReducer(state, action);
}
