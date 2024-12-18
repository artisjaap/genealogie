import {RouterModule, Routes} from '@angular/router';
import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {isIngelogged} from "./login/guard/AuthGuard";

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    pathMatch: 'full',
    redirectTo: 'personen/zoeken',
  },
  {
    path: 'admin',
    canActivate: [isIngelogged],
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
  },
  {
    path: 'personen',
    canActivate: [isIngelogged],
    loadChildren: () => import('./personen/personen.module').then(m => m.PersonenModule),
  },
  {
    path: 'login',
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
  },
 ];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
