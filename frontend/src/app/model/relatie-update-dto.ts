export class RelatieUpdateDto {
  public constructor(
    public id: number,
    public huwelijkDatum: Date | null | undefined,
    public gemeente: string | null | undefined,
    public uitElkaar: Boolean | null | undefined,) {
  }
}
