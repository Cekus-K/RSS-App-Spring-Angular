import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './components/login/login.component';
import {RssComponent} from './components/rss/rss.component';
import {LogoutComponent} from './components/logout/logout.component';
import {RegisterComponent} from './components/register/register.component';


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'rss', component: RssComponent},
  {path: 'logout', component: LogoutComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
