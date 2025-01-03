import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DocumentTypeDto} from "../model/genealogie-dto";

@Injectable({
  providedIn: 'root'
})
export class ReferentieDataService {
  constructor(private http: HttpClient) {

  }


  laadDocumentTypes(): Observable<DocumentTypeDto[]> {
    return this.http.get<DocumentTypeDto[]>(`api/referentie/document-types`)

  }
}
