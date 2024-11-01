export class NatuurlijkPersoonDto {
  public constructor(public id: number,
                     public naam: string,
                     public voornaam: string,
                     public geborenOp: Date,
                     public geborenTe: string,
                     public overledenOp: Date,
                     public overledenTe: string) {
  }
}
