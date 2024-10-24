import { Routes } from '@angular/router';
import { UserComponent } from './pages/user/user.component';
import { TreinoComponent } from './pages/treino/treino.component';
import { ExercicioComponent } from './components/exercicio/exercicio.component';
import { HomeComponent } from './pages/home/home.component';
import { ProfileComponent } from './pages/profile/profile.component';

export const routes: Routes = [
    { path: 'home', component: HomeComponent},
    { path: 'profile/:id', component: ProfileComponent },
    { path: 'users', component: UserComponent },
    { path: 'treinos', component: TreinoComponent },
    { path: 'exercicios', component: ExercicioComponent },
    { path: '', redirectTo: '/home', pathMatch: 'full' }
];
