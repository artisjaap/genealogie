import {Action, createReducer, on} from '@ngrx/store';
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";
import {
  documentOpgeladen,
  documentTypesGeladen,
  natuurlijkPersoonFicheGeladen, natuurlijkPersoonVoorRelatieAangemaakt, oudersVanNatuurlijkPersoonAangemaakt,
  personenGevonden, sluitDocumentPopup, sluitOudersVanPersoon, sluitPersoonVoorRelatie,
  toonDocumentPopup, toonOudersVanPersoon, toonPersoonVoorRelatie
} from "./personen.acties";
import {NatuurlijkPersoonFicheDto} from "../../model/natuurlijk-persoon-fiche-dto";
import {DocumentTypeDto} from "../../model/document-type-dto";
import {DialogData} from "../../model/document-upload-data-dts";

export const personenInfoFeatureKey = 'personenInfo';


export interface PersoonDataState {
  gevondenPersonen: NatuurlijkPersoonDto[],
  geladenPersoonFiche: NatuurlijkPersoonFicheDto | undefined,
  documentTypes: DocumentTypeDto[],
  popups : {
    toonDocumentPopup: {
      state: boolean,
      dialogData: DialogData | null,
    },
    toonPersoonVoorRelatie: {
      state: boolean,
      dialogData: DialogData | null,
    },
    toonOudersVoorPersoon: {
      state: boolean,
      dialogData: DialogData | null,
    },
  }

}

export const personenInitialState: PersoonDataState = {
  gevondenPersonen: [],
  geladenPersoonFiche: undefined,
  documentTypes: [],
  popups : {
    toonDocumentPopup: {
      state: false,
      dialogData: null
    },
    toonPersoonVoorRelatie: {
      state: false,
      dialogData: null
    },
    toonOudersVoorPersoon: {
      state: false,
      dialogData: null
    },
  }
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
  on(toonDocumentPopup, (state: PersoonDataState, {dialogData}) => ({
    ...state,
    popups: {
      ...state.popups,
      toonDocumentPopup: {
        state: true,
        dialogData: dialogData
      }
    }
  })),
  on(toonPersoonVoorRelatie, (state: PersoonDataState, {dialogData}) => ({
    ...state,
    popups: {
      ...state.popups,
      toonPersoonVoorRelatie: {
        state: true,
        dialogData: dialogData
      }
    }
  })),
  on(toonOudersVanPersoon, (state: PersoonDataState, {dialogData}) => ({
    ...state,
    popups: {
      ...state.popups,
      toonOudersVoorPersoon: {
        state: true,
        dialogData: dialogData
      }
    }
  })),
  on(natuurlijkPersoonVoorRelatieAangemaakt, (state: PersoonDataState) => ({
    ...state,
    popups: {
      ...state.popups,
      toonPersoonVoorRelatie: {
        state: false,
        dialogData: null
      }
    }
  })),
  on(oudersVanNatuurlijkPersoonAangemaakt, (state: PersoonDataState) => ({
    ...state,
    popups: {
      ...state.popups,
      toonOudersVoorPersoon: {
        state: false,
        dialogData: null
      }
    }
  })),
  on(sluitDocumentPopup, (state: PersoonDataState) => ({
  ...state,
  popups: {
    ...state.popups,
    toonDocumentPopup: {
      state: false,
      dialogData: null
    }
  }
})),
  on(documentOpgeladen, (state: PersoonDataState) => ({
    ...state,
    popups: {
      ...state.popups,
      toonDocumentPopup: {
        state: false,
        dialogData: null
      }
    }
  })),
  on(sluitOudersVanPersoon, (state: PersoonDataState) => ({
    ...state,
    popups: {
      ...state.popups,
      toonOudersVoorPersoon: {
        state: false,
        dialogData: null
      }
    }
  })),
  on(sluitPersoonVoorRelatie, (state: PersoonDataState) => ({
    ...state,
    popups: {
      ...state.popups,
      toonPersoonVoorRelatie: {
        state: false,
        dialogData: null
      }
    }
  }))
);

export function thePersonenReducer(state: PersoonDataState | undefined, action: Action) {
  return createThePersonenReducer(state, action);
}
