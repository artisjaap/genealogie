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

export class BroerOfZusDto {
  public constructor(public natuurlijkPersoon: NatuurlijkPersoonDto,
                     public isHalf: boolean) {
  }
}

export class NonkelOfTanteDto {
  public constructor(public natuurlijkPersoon: NatuurlijkPersoonDto,
                     public aangetrouwd: AangetrouwdDto[]) {
  }
}

export class AangetrouwdDto {
  public constructor(public natuurlijkPersoon:NatuurlijkPersoonDto,
                     public actief: boolean) {

  }
}

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

export class DialogData {
  constructor(public natuurlijkPersoon: NatuurlijkPersoonDto | null,  public relatie: RelatieDto | null ){}
}

export class DocumentTypeDto {
  public constructor(public id: number,
                     public omschrijving: string,
  ) {
  }
}

export class GenealogischDriehoekjeDto {
  public constructor(
    public kind: NatuurlijkPersoonDto,
    public moeder: NatuurlijkPersoonDto,
    public vader: NatuurlijkPersoonDto) {}

}

export class KindUitRelatieDto {
  public constructor(public relatie:RelatieIdDto,
                     public kind: NatuurlijkPersoonDto) {
  }
}

export class OudersVanKindDto {
  public constructor(
    public moeder:NatuurlijkPersoonDto,
    public vader:NatuurlijkPersoonDto,
    public kind:NatuurlijkPersoonDto|undefined
  ) {
  }
}

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

export class RelatieMetNieuwNatuurlijkPersoonDto {
  public constructor(
    public persoon1:NatuurlijkPersoonDto | null | undefined,
    public persoon2:NatuurlijkPersoonDto,
    public gehuwdOp:Date | null,
    public gehuwdTe:string | null,

  ) {
  }
}

export class RelatieUpdateDto {
  public constructor(
    public id: number,
    public huwelijkDatum: Date | null | undefined,
    public gemeente: string | null | undefined,
    public uitElkaar: Boolean | null | undefined,) {
  }
}

export class ScheidingDto {
  public constructor(
    public id: number,
    public datumScheiding: Date | null
  ){
  }
}

export class StamboomEchartDto{
  constructor(
    name: string,
    children: StamboomEchartDto[],
  ) { }
}

