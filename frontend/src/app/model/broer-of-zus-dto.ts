import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";

export class BroerOfZusDto {
  public constructor(public natuurlijkPersoon: NatuurlijkPersoonDto,
                     public isHalf: boolean) {
  }
}
