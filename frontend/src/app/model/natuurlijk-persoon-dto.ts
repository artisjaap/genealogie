export class NatuurlijkPersoonDto {
  public constructor(public id: number,
                     public naam: string,
                     public voornaam: string,
                     public geslacht: string,
                     public geborenOp: Date | null,
                     public geborenTe: string | null,
                     public overledenOp: Date | null,
                     public overledenTe: string | null,
                     public leeftijd: number | null,
                     public sterrenbeeld: string | null) {
  }
}
