import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";
import {RelatieDto} from "./relatie-dto";

export class DialogData {
  constructor(public natuurlijkPersoon: NatuurlijkPersoonDto | null,  public relatie: RelatieDto | null ){}
}
