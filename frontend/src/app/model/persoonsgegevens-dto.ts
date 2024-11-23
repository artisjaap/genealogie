export class PersoonsgegevensDto {

  public constructor(
    public id: number|null|undefined,
    public naam: string | null | undefined,
    public voornaam: string | null| undefined,
    public geslacht: string | null| undefined,
    public geborenOp: Date | null| undefined,
    public geborenTe: string | null| undefined,
    public overledenOp: Date | null| undefined,
    public overledenTe: string | null| undefined) {
  }
}
