import {RelatieDto, RelatieIdDto} from "./relatie-dto";
import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";

export class KindUitRelatieDto {
  public constructor(public relatie:RelatieIdDto,
                     public kind: NatuurlijkPersoonDto) {
  }
}
