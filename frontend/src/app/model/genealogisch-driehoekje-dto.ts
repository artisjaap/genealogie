import {NatuurlijkPersoonDto} from "./natuurlijk-persoon-dto";

export class GenealogischDriehoekjeDto {
  public constructor(
    public kind: NatuurlijkPersoonDto,
    public moeder: NatuurlijkPersoonDto,
    public vader: NatuurlijkPersoonDto) {}

}
