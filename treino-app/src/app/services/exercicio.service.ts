import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exercicio } from '../model/Exercicio';

@Injectable({
  providedIn: 'root'
})
export class ExercicioService {

  private apiUrl = 'http://localhost:8080/api/exercicios'; // URL da API

    constructor(private http: HttpClient) {}

    // Método para obter exercícios de um treino específico
    getExerciciosPorTreino(treinoId: number): Observable<Exercicio[]> {
      return this.http.get<Exercicio[]>(`http://localhost:8080/api/treinos/${treinoId}/exercicios`);
  }
}
