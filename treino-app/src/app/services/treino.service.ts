import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Treino } from '../model/Treino';

@Injectable({
  providedIn: 'root'
})
export class TreinoService {
  
  private apiUrl = 'http://localhost:8080/api/treinos'; // URL da API

  constructor(private http: HttpClient) {}

  // Método para obter treinos de um usuário específico
    getTreinosByUserId(userId: number): Observable<Treino[]> {
      return this.http.get<Treino[]>(`${this.apiUrl}/${userId}/treinos`);
  }
}
