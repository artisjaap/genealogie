import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";

export class RelatieMetNieuwNatuurlijkPersoonDto {
  public constructor(
    public persoon1:NatuurlijkPersoonDto | null | undefined,
    public persoon2:NatuurlijkPersoonDto,
    public gehuwdOp:Date | null,
    public gehuwdTe:string | null,

  ) {
  }
}

