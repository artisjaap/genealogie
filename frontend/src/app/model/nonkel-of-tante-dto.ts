import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";

export class NonkelOfTanteDto {
  public constructor(public natuurlijkPersoon: NatuurlijkPersoonDto,
                     public aangetrouwd: AangetrouwdDto[]) {
  }
}

export class AangetrouwdDto {
  public constructor(public natuurlijkPersoon:NatuurlijkPersoonDto,
                     public actief: boolean) {

  }
}
