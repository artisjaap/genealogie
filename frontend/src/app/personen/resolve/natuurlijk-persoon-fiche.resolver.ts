import {inject} from '@angular/core';
import {ActivatedRouteSnapshot} from '@angular/router';
import {filter, finalize, first, tap} from 'rxjs';
import {select, Store} from '@ngrx/store';
import {map} from 'rxjs/operators';
import {PersonenState} from "../store/personen.reducer";
import {getGeladenPeroonFiche} from "../store/personen.selector";
import {laadNatuurlijkPersoonFiche} from "../store/personen.acties";

export const NatuurlijkPersoonFicheResolver = (route: ActivatedRouteSnapshot) =>{

  const store = inject(Store<PersonenState>);
  let bezigMetLadenFiche = false;


  return store.pipe(
    select(getGeladenPeroonFiche),
    map(persoon => persoon?.natuurlijkPersoon.id == route.params['id']), //FIXME natuurlijkPersoon.id is int en route param is string, route id doorgeven als parameter aan resolver?
    tap(juistePersoonIsGeladen => {
      if(!bezigMetLadenFiche && !juistePersoonIsGeladen){
        bezigMetLadenFiche = true;
        store.dispatch(laadNatuurlijkPersoonFiche({natuurlijkPersoonId: route.params['id']}));
      }
    }),
    filter(dataGeladen => dataGeladen),
    first(),
    finalize(() => bezigMetLadenFiche = false)
  );

}
