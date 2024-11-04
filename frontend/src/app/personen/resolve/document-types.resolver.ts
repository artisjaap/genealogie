import {inject} from '@angular/core';
import {filter, finalize, first, tap} from 'rxjs';
import {select, Store} from '@ngrx/store';
import {map} from 'rxjs/operators';
import {PersonenState} from "../store/personen.reducer";
import {getGeladenDocumentTypes} from "../store/personen.selector";
import {laadDocumentTypes} from "../store/personen.acties";

export const DocumentTypesResolver = () =>{

  const store = inject(Store<PersonenState>);
  let bezigMetDocumentTypesLaden = false;


  return store.pipe(
    select(getGeladenDocumentTypes),
    map(types => types.length !== 0),
    tap(typesGeladen => {
      if(!bezigMetDocumentTypesLaden && !typesGeladen){
        bezigMetDocumentTypesLaden = true;
        store.dispatch(laadDocumentTypes());
      }
    }),
    filter(dataGeladen => dataGeladen),
    first(),
    finalize(() => bezigMetDocumentTypesLaden = false)
  );

}
