import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {NatuurlijkPersoonDto} from "../model/natuurlijk-persoon-dto";
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

}
