import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { UserComponent } from './pages/user/user.component';


export const routes: Routes = [
    { path: 'home', component: HomeComponent},
    {path: 'user/:id', component: UserComponent},
    { path: '', redirectTo: '/home', pathMatch: 'full' }
];
