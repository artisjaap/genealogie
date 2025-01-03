import {Action, createReducer, on} from '@ngrx/store';
import {
  documentOpgeladen,
  documentTypesGeladen,
  nakomelingenVanGeladen,
  natuurlijkPersoonFicheGeladen,
  natuurlijkPersoonVoorRelatieAangemaakt,
  oudersVanNatuurlijkPersoonAangemaakt,
  personenGevonden,
  relatieMetNatuurlijkPersoonAangemaakt,
  sluitDocumentPopup,
  sluitOudersVanPersoon,
  sluitPersoonVoorRelatie,
  sluitVoegRelatieToeMet,
  toonDocumentPopup,
  toonOudersVanPersoon,
  toonPersoonVoorRelatie,
  toonVoegRelatieToeMet,
  vooroudersVanGeladen
} from "./personen.acties";
import {
  DialogData,
  DocumentTypeDto,
  NatuurlijkPersoonDto,
  NatuurlijkPersoonFicheDto,
  StamboomEchartDto
} from "../../model/genealogie-dto";

export const personenInfoFeatureKey = 'personenInfo';


export interface PersoonDataState {
  gevondenPersonen: NatuurlijkPersoonDto[],
  geladenPersoonFiche: NatuurlijkPersoonFicheDto | undefined,
  vooroudersVan: StamboomEchartDto | undefined,
  nakomelingenVan: StamboomEchartDto | undefined,
  documentTypes: DocumentTypeDto[],
  popups: {
    toonDocumentPopup: {
      state: boolean,
      dialogData: DialogData | null
    },
    toonPersoonVoorRelatie: {
      state: boolean,
      dialogData: DialogData | null
    },
    toonOudersVoorPersoon: {
      state: boolean,
      dialogData: DialogData | null
    },
    toonVoegRelatieToeMet: {
      state: boolean,
      dialogData: DialogData | null
    }
  }

}

export const personenInitialState: PersoonDataState = {
  gevondenPersonen: [],
  geladenPersoonFiche: undefined,
  vooroudersVan: undefined,
  nakomelingenVan: undefined,
  documentTypes: [],
  popups: {
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
    toonVoegRelatieToeMet: {
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
  on(toonVoegRelatieToeMet, (state: PersoonDataState, {dialogData}) => ({
    ...state,
    popups: {
      ...state.popups,
      toonVoegRelatieToeMet: {
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
  on(relatieMetNatuurlijkPersoonAangemaakt, (state: PersoonDataState) => ({
    ...state,
    popups: {
      ...state.popups,
      toonVoegRelatieToeMet: {
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
  })),
  on(sluitVoegRelatieToeMet, (state: PersoonDataState) => ({
    ...state,
    popups: {
      ...state.popups,
      toonVoegRelatieToeMet: {
        state: false,
        dialogData: null
      }
    }
  })),
  on(nakomelingenVanGeladen, (state: PersoonDataState, {stamboom}) => ({
    ...state,
    nakomelingenVan: stamboom
  })),
  on(vooroudersVanGeladen, (state: PersoonDataState, {stamboom}) => ({
    ...state,
    vooroudersVan: stamboom
  }))
);

export function thePersonenReducer(state: PersoonDataState | undefined, action: Action) {
  return createThePersonenReducer(state, action);
}
