import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExercicioService {
  private apiUrl = 'http://localhost:8080/api/exercicios';

  constructor(private http: HttpClient) { }

  criarExercicio(treinoId: string, exercicio: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/treino/${treinoId}`, exercicio);
  }

  getExerciciosByTreino(treinoId: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.apiUrl}/treino/${treinoId}`);
  }

  deletarExercicio(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  atualizarExercicio(id: string, exercicio: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, exercicio);
  }
}
