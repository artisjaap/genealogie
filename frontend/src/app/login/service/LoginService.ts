import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoginResponseDto, LoginDto, RegistreerGebruikerDto} from "../../model/login-dto";

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor (private http: HttpClient) {
  }

  registreerGebruiker(registreerGebruiker:RegistreerGebruikerDto): Observable<LoginResponseDto> {
    return this.http.post<LoginResponseDto>("auth/signup", registreerGebruiker)
  }

  login(login:LoginDto): Observable<LoginResponseDto> {
    return this.http.post<LoginResponseDto>("auth/login", login)
  }
}
