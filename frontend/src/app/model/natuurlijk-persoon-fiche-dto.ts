import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";
import {RelatieDto} from "./relatie-dto";
import {BroerOfZusDto} from "./broer-of-zus-dto";
import {NonkelOfTanteDto} from "./nonkel-of-tante-dto";

export class NatuurlijkPersoonFicheDto {
  public constructor(public natuurlijkPersoon: NatuurlijkPersoonDto,
                     public moeder: NatuurlijkPersoonDto | null,
                     public vader: NatuurlijkPersoonDto | null,
                     public relaties: RelatieDto[],
                     public kinderen: NatuurlijkPersoonDto[],
                     public broersEnZussen: BroerOfZusDto[],
                     public nonkelsEnTantes: NonkelOfTanteDto[],
                     public nevenEnNichten: NatuurlijkPersoonDto[],
                     public documenten: any[]) {
  }
}


