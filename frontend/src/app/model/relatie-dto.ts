import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";

export class RelatieDto {
  public constructor(public id: number,
                     public persoon1: NatuurlijkPersoonDto,
                     public persoon2: NatuurlijkPersoonDto,
                     public gehuwdOp: Date | null,
                     public gehuwdTe: string | null,
                     public kinderen: NatuurlijkPersoonDto[] | null,
                     public uitElkaar: Boolean | null) {
  }
}

export class RelatieIdDto {
  public constructor(public id: number) {
  }
}
