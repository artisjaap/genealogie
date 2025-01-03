import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {
  GenealogischDriehoekjeDto,
  KindUitRelatieDto,
  NatuurlijkPersoonDto,
  NatuurlijkPersoonFicheDto,
  OudersVanKindDto,
  PersoonsgegevensDto,
  RelatieDto,
  RelatieMetNieuwNatuurlijkPersoonDto,
  RelatieUpdateDto,
  ScheidingDto,
  StamboomEchartDto
} from "../model/genealogie-dto";
import {Observable} from "rxjs";


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
