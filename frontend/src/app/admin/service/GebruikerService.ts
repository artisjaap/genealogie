import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {GebruikerDto} from "../../model/login-dto";

@Injectable()
export class GebruikerService {
  constructor(private http: HttpClient) {

  }

  public alleGebruikers(): Observable<GebruikerDto> {
    return this.http.get<GebruikerDto>("/api/gebruikers")
  }

  public voegRoleToeAanGebruiker(gerbuikerRole: string, gebruikerId: number): Observable<GebruikerDto> {
    return this.http.put<GebruikerDto>(`/api/gebruikers/${gebruikerId}/${gerbuikerRole}`, null);
  }
}

