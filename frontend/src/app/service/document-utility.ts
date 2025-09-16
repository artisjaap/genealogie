import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { finalize, of, switchMap, tap } from "rxjs";

@Injectable()
export class DocumentUtility {
  private imageUrlMap = new Map<number, string>();
  private inFlightIds = new Set<number>();

  constructor(private http: HttpClient) {}

  getDocumentUrl(id: number): string | null {
    const existing = this.imageUrlMap.get(id) || null;
    if (!existing && !this.inFlightIds.has(id)) {
      this.fetchDocument(id);
    }
    return existing;
  }

  private fetchDocument(id: number): void {
    this.inFlightIds.add(id);

    of(id)
      .pipe(
        switchMap(() =>
          this.http.get(`/api/document/${id}`, { responseType: "blob" })
        ),
        tap((blob) => {
          const url = URL.createObjectURL(blob);
          this.imageUrlMap.set(id, url);
        }),
        finalize(() => this.inFlightIds.delete(id))
      )
      .subscribe({
        error: () => {
          // Optionally: set a placeholder or log
        },
      });
  }

  destroy(): void {
    this.imageUrlMap.forEach((url) => URL.revokeObjectURL(url));
    this.imageUrlMap.clear();
  }
}
