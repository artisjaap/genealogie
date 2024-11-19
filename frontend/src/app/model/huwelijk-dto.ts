export class HuwelijkDto {
  public constructor(
    public id: number,
    public huwelijkDatum: Date | null,
    public gemeente: String | null) {
  }
}
