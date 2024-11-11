import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";

export class OudersVanKindDto {
  public constructor(
    public moeder:NatuurlijkPersoonDto,
    public vader:NatuurlijkPersoonDto,
    public kind:NatuurlijkPersoonDto|undefined
  ) {
  }
}

