import {Action, createReducer, on} from '@ngrx/store';
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";
import {documentTypesGeladen, natuurlijkPersoonFicheGeladen, personenGevonden} from "./personen.acties";
import {NatuurlijkPersoonFicheDto} from "../../model/natuurlijk-persoon-fiche-dto";
import {DocumentTypeDto} from "../../model/document-type-dto";

export const personenInfoFeatureKey = 'personenInfo';


export interface PersoonDataState {
  gevondenPersonen: NatuurlijkPersoonDto[],
  geladenPersoonFiche: NatuurlijkPersoonFicheDto | undefined,
  documentTypes: DocumentTypeDto[]

}

export const personenInitialState: PersoonDataState = {
  gevondenPersonen: [],
  geladenPersoonFiche: undefined,
  documentTypes: []
};


const createThePersonenReducer = createReducer(
  personenInitialState,
  on(personenGevonden, (state: PersoonDataState, {personen}) => ({
    ...state,
    gevondenPersonen: personen

  })),
  on(natuurlijkPersoonFicheGeladen, (state: PersoonDataState, {natuurlijkPersoonFiche}) => ({
    ...state,
    geladenPersoonFiche: natuurlijkPersoonFiche

  })),
  on(documentTypesGeladen, (state: PersoonDataState, {documentTypes}) => ({
    ...state,
    documentTypes: documentTypes

  })),
);

export function thePersonenReducer(state: PersoonDataState | undefined, action: Action) {
  return createThePersonenReducer(state, action);
}
