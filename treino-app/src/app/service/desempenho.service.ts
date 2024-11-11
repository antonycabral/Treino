import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DesempenhoData } from '../../Models/DesenpenhoData';
import { TreinoRegistro } from '../../Models/TreinoRegistro';

@Injectable({
  providedIn: 'root'
})
export class DesempenhoService {

  private apiUrl = 'http://localhost:8080/api/desempenho';

  constructor(private http: HttpClient) { }

  private getHeaders() {
    const token = localStorage.getItem('token');
    return {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      })
    };
  }

  getDesempenhoUsuario(userId: string): Observable<DesempenhoData> {
    return this.http.get<DesempenhoData>(`${this.apiUrl}/usuario/${userId}`, this.getHeaders());
  }

  registrarTreino(treinoData: TreinoRegistro): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/registrar`, treinoData, this.getHeaders());
  }
}
