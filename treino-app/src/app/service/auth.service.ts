import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

interface AuthResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) {}

  private getHeaders() {
    const token = localStorage.getItem('token');
    return {
        headers: new HttpHeaders({
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        })
    };
}

cadastrar(usuario: any): Observable<any> {
  return this.http.post(`${this.baseUrl}/cadastrar`, usuario);
}

login(credentials: any): Observable<AuthResponse> {
  return this.http.post<AuthResponse>(`${this.baseUrl}/login`, credentials)
    .pipe(
      tap((response: AuthResponse) => {
        localStorage.setItem('token', response.token);
        localStorage.setItem('userEmail', credentials.email);
      })
    );
}

}
