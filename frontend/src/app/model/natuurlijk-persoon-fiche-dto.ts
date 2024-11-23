import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";
import {RelatieDto} from "./relatie-dto";

export class NatuurlijkPersoonFicheDto {
  public constructor(public natuurlijkPersoon: NatuurlijkPersoonDto,
                     public moeder: NatuurlijkPersoonDto | null,
                     public vader: NatuurlijkPersoonDto | null,
                     public relaties: RelatieDto[],
                     public kinderen: NatuurlijkPersoonDto[],
                     public broersEnZussen: NatuurlijkPersoonDto[],
                     public oomsEnTantes: NatuurlijkPersoonDto[],
                     public nevenEnNichten: NatuurlijkPersoonDto[],
                     public documenten: any[]) {
  }
}


