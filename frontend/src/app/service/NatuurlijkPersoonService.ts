import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {NatuurlijkPersoonDto} from "../model/natuurlijk-persoon-dto";
import {Observable, of} from "rxjs";
import {NatuurlijkPersoonFicheDto} from "../model/natuurlijk-persoon-fiche-dto";
import {OudersVanKindDto} from "../model/ouders-van-kind-dto";
import {KindUitRelatieDto} from "../model/kind-uit-relatie-dto";
import {GenealogischDriehoekjeDto} from "../model/genealogisch-driehoekje-dto";

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

  zoekPersonen(zoekString: string):Observable<NatuurlijkPersoonDto[]> {
    const options =
      { params: new HttpParams().set('zoekString', zoekString) };
    return this.http.get<NatuurlijkPersoonDto[]>("api/natuurlijk-persoon/zoek", options)

  }

  laadFicheVoorNatuurlijkPersoon(id:number):Observable<NatuurlijkPersoonFicheDto> {
    return this.http.get<NatuurlijkPersoonFicheDto>(`api/natuurlijk-persoon/${id}`)

  }
}
