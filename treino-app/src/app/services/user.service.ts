import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/User';
import { Treino } from '../model/Treino';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/api/usuarios'; // URL da API

    constructor(private http: HttpClient) {}

    // Método para obter todos os usuários
    getUsers(): Observable<User[]> {
        return this.http.get<User[]>(this.apiUrl);
    }

    getTreinosByUserId(userId: number): Observable<Treino[]> {
      return this.http.get<Treino[]>(`${this.apiUrl}/${userId}/treinos`);
  }
}
