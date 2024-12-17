export class LoginDto {
  public constructor(public email: string,
                     public password: string,) {
  }
}

export class RegistreerGebruikerDto {
  public constructor(public naam: string,
                     public voornaam: string,
                     public email: string,
                     public password: string) {
  }
}

export class GebruikerDto {
  public constructor(public naam: string,
                     public email: string,
                     public token: string) {
  }
}
