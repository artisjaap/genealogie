import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {NatuurlijkPersoonDto} from "../model/natuurlijk-persoon-dto";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NatuurlijkPersoonService {
  constructor (private http: HttpClient) {

  }

  natuurlijkPersoonOpslaan(natuurlijkPersoon:any): Observable<NatuurlijkPersoonDto> {
    return this.http.post<NatuurlijkPersoonDto>("api/natuurlijk-persoon", natuurlijkPersoon)
  }

  zoekPersonen(zoekString: string):Observable<NatuurlijkPersoonDto[]> {
    return of([
      new NatuurlijkPersoonDto(1, "Coene", "Stijn", new Date(1981,5, 3), 'Zele', null, null)
    ]);

  }
}
