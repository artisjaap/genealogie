import {createAction, props} from "@ngrx/store";
import {NatuurlijkPersoonDto} from "../../model/natuurlijk-persoon-dto";
import {NatuurlijkPersoonFicheDto} from "../../model/natuurlijk-persoon-fiche-dto";
import {DocumentTypeDto} from "../../model/document-type-dto";
import {RelatieDto} from "../../model/relatie-dto";
import {GenealogischDriehoekjeDto} from "../../model/genealogisch-driehoekje-dto";
import {DialogData} from "../../model/document-upload-data-dts";
import {HuwelijkDto} from "../../model/huwelijk-dto";
import {StamboomEchartDto} from "../../model/stamboom-echart-dto";
import {PersoonsgegevensDto} from "../../model/persoonsgegevens-dto";

export const maakNieuwNatuurlijkPersoon = createAction('[NATUURLIJK PERSOON - Nieuw natuurlijk persoon]');
export const nieuwNatuurlijkPersoonAangemaakt = createAction('[NATUURLIJK PERSOON - Nieuw natuurlijk persoon aangemaakt]', props<{natuurlijkPersoon: NatuurlijkPersoonDto}>());
export const maakOudersVanNatuurlijkPersoon = createAction('[NATUURLIJK PERSOON - Maak Ouders van natuurlijk persoon]');
export const oudersVanNatuurlijkPersoonAangemaakt = createAction('[NATUURLIJK PERSOON - Ouders van natuurlijk persoon aangemaakt]', props<{genealogischDriehoekje: GenealogischDriehoekjeDto}>());
export const maakNatuurlijkPersoonVoorRelatie = createAction('[NATUURLIJK PERSOON - Maak natuurlijk persoon voor relatie]', props<{relatie: RelatieDto|null}>());
export const natuurlijkPersoonVoorRelatieAangemaakt = createAction('[NATUURLIJK PERSOON - natuurlijk persoon voor relatie aangemaakt]', props<{genealogischDriehoekje: GenealogischDriehoekjeDto}>());
export const maakRelatieMet = createAction('[NATUURLIJK PERSOON - maak relatie met]');
export const relatieMetNatuurlijkPersoonAangemaakt = createAction('[NATUURLIJK PERSOON - natuurlijk persoon met relatie aangemaakt]', props<{relatie: RelatieDto}>());

export const toonDocumentPopup = createAction('[NATUURLIJK PERSOON - toon document popup]', props<{dialogData: DialogData}>());
export const sluitDocumentPopup = createAction('[NATUURLIJK PERSOON - sluit document popup]');
export const toonPersoonVoorRelatie = createAction('[NATUURLIJK PERSOON - toon persoon voor relatie popup]', props<{dialogData: DialogData}>());
export const sluitPersoonVoorRelatie = createAction('[NATUURLIJK PERSOON - sluit persoon voor relatie popup]');
export const toonOudersVanPersoon = createAction('[NATUURLIJK PERSOON - toon ouders voor persoon popup]', props<{dialogData: DialogData}>());
export const sluitOudersVanPersoon = createAction('[NATUURLIJK PERSOON - sluit ouders voor persoon popup]');
export const toonVoegRelatieToeMet = createAction('[NATUURLIJK PERSOON - toon vroeg relatie toe met]', props<{dialogData: DialogData}>());
export const sluitVoegRelatieToeMet = createAction('[NATUURLIJK PERSOON - sluit voeg relatie toe moet]');

export const zoekPersonen = createAction('[NATUURLIJK PERSOON - zoeken]', props<{zoekString: string}>());
export const personenGevonden = createAction('[NATUURLIJK PERSOON - gevonden]', props<{personen: NatuurlijkPersoonDto[]}>());

export const laadNatuurlijkPersoonFiche = createAction('[NATUURLIJK PERSOON - Laad fiche]', props<{natuurlijkPersoonId: number}>());
export const natuurlijkPersoonFicheGeladen = createAction('[NATUURLIJK PERSOON - fiche geladen]', props<{natuurlijkPersoonFiche: NatuurlijkPersoonFicheDto}>());

export const laadDocumentTypes = createAction('[NATUURLIJK PERSOON - Laad document types]');
export const documentTypesGeladen = createAction('[NATUURLIJK PERSOON - Document types geladen]', props<{documentTypes: DocumentTypeDto[]}>());

export const documentOpgeladen = createAction('[NATUURLIJK PERSOON] document opgeladen');

export const wijzigHuwelijk = createAction('[NATUURLIJK PERSOON] wijzig huwelijk', props<{huwelijk: HuwelijkDto}>());
export const huwelijkGewijzigd = createAction('[NATUURLIJK PERSOON] huwelijk gewijzigd');
export const wijzigPersoonsgegevens = createAction('[NATUURLIJK PERSOON] wijzig persoonsgegevens', props<{persoonsgegevens: PersoonsgegevensDto}>());
export const persoonsgegevensGewijzigd = createAction('[NATUURLIJK PERSOON] persoonsgegevens gewijzigd');


export const laadVooroudersVan = createAction('[NATUURLIJK PERSOON - laad voorouders van]', props<{natuurlijkPersoonId:number}>());
export const vooroudersVanGeladen = createAction('[NATUURLIJK PERSOON -  voorouders van geladen]', props<{stamboom: StamboomEchartDto}>());
export const laadNakomelingenVan = createAction('[NATUURLIJK PERSOON - laad laadNakomelingen van]', props<{natuurlijkPersoonId:number}>());
export const nakomelingenVanGeladen = createAction('[NATUURLIJK PERSOON - laadNakomelingen van geladen]', props<{stamboom: StamboomEchartDto}>());
