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

export class LoginUserResponseDto {
  public constructor(public naam: string,
                     public voornaam: string,
                     public email: string){

  }
}

export class LoginResponseDto {
  public constructor(public gebruiker: LoginUserResponseDto,
                     public expiresIn: number,
                     public token: string) {
  }
}
