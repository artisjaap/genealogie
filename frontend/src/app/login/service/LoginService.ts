import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {GebruikerDto, LoginDto, RegistreerGebruikerDto} from "../../model/login-dto";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor (private http: HttpClient) {
  }

  registreerGebruiker(registreerGebruiker:RegistreerGebruikerDto): Observable<GebruikerDto> {
    return this.http.post<GebruikerDto>("auth/signup", registreerGebruiker)
  }

  login(login:LoginDto): Observable<GebruikerDto> {
    return this.http.post<GebruikerDto>("auth/login", login)
  }
}
