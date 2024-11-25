import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {NatuurlijkPersoonDto} from "../model/natuurlijk-persoon-dto";
import {Observable} from "rxjs";
import {NatuurlijkPersoonFicheDto} from "../model/natuurlijk-persoon-fiche-dto";
import {OudersVanKindDto} from "../model/ouders-van-kind-dto";
import {KindUitRelatieDto} from "../model/kind-uit-relatie-dto";
import {GenealogischDriehoekjeDto} from "../model/genealogisch-driehoekje-dto";
import {RelatieDto} from "../model/relatie-dto";
import {RelatieMetNieuwNatuurlijkPersoonDto} from "../model/relatie-met-nieuw-natuurlijk-persoon-dto";
import {RelatieUpdateDto} from "../model/relatie-update-dto";
import {ScheidingDto} from "../model/scheiding-dto";
import {StamboomEchartDto} from "../model/stamboom-echart-dto";
import {PersoonsgegevensDto} from "../model/persoonsgegevens-dto";

@Injectable({
  providedIn: 'root'
})
export class NatuurlijkPersoonService {
  constructor (private http: HttpClient) {

  }

  natuurlijkPersoonOpslaan(natuurlijkPersoon:any): Observable<NatuurlijkPersoonDto> {
    return this.http.post<NatuurlijkPersoonDto>("api/natuurlijk-persoon", natuurlijkPersoon)
  }


  oudersVanNatuurlijkPersoonToevoegen(ouders: OudersVanKindDto): Observable<GenealogischDriehoekjeDto>{
    return this.http.post<GenealogischDriehoekjeDto>("api/genealogie/registreerd-ouders", ouders)
  }

  persoonAanRelatieToevoegen(kindUitRelatieDto: KindUitRelatieDto): Observable<GenealogischDriehoekjeDto>{
    return this.http.post<GenealogischDriehoekjeDto>("api/genealogie/kind-uit-relatie", kindUitRelatieDto)
  }

  maakRelatieMet(relatieMet: RelatieMetNieuwNatuurlijkPersoonDto) : Observable<RelatieDto> {
    return this.http.post<RelatieDto>("api/genealogie/registreerd-relatie-met", relatieMet);
  }

  zoekPersonen(zoekString: string):Observable<NatuurlijkPersoonDto[]> {
    const options =
      { params: new HttpParams().set('zoekString', zoekString) };
    return this.http.get<NatuurlijkPersoonDto[]>("api/natuurlijk-persoon/zoek", options)

  }

  laadFicheVoorNatuurlijkPersoon(id:number):Observable<NatuurlijkPersoonFicheDto> {
    return this.http.get<NatuurlijkPersoonFicheDto>(`api/natuurlijk-persoon/${id}`)

  }

  wijzigHuwelijk(wijzigHuwelijk: RelatieUpdateDto): Observable<RelatieDto> {
    return this.http.put<RelatieDto>(`api/genealogie/relatie/${wijzigHuwelijk.id}`, wijzigHuwelijk);
  }

  wijzigPersoonsgegevens(wijzigPersoonsgegevens: PersoonsgegevensDto): Observable<NatuurlijkPersoonFicheDto> {
    return this.http.put<NatuurlijkPersoonFicheDto>(`api/natuurlijk-persoon/${wijzigPersoonsgegevens.id}`, wijzigPersoonsgegevens);
  }

  scheidingToevoegen(scheiding: ScheidingDto): Observable<RelatieDto> {
    return this.http.put<RelatieDto>(`api/genealogie/relatie/${scheiding.id}/scheiding`, scheiding);
  }


  nakomelingenVan(natuurlijkPersoonId: number): Observable<StamboomEchartDto> {
    return this.http.get<StamboomEchartDto>(`api/genealogie/nakomelingen/${natuurlijkPersoonId}`);
  }


  vooroudersVan(natuurlijkPersoonId: number): Observable<StamboomEchartDto> {
    return this.http.get<StamboomEchartDto>(`api/genealogie/voorouders/${natuurlijkPersoonId}`);
  }



}
