import {RouterModule, Routes} from '@angular/router';
import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";

const routes: Routes = [
  {
    path: '',
    component: AppComponent
  },
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
  },
  {
    path: 'personen',
    loadChildren: () => import('./personen/personen.module').then(m => m.PersonenModule),
  },
 ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
