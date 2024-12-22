import { Injectable } from '@angular/core';
import { Minister } from '../interfaces/minister';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { AuthGoogleService } from './auth-google.service';

@Injectable({
  providedIn: 'root',
})
export class MinisterService {

  constructor(private httpClient: HttpClient, private authService: AuthGoogleService) { }
  private apiUrl = "http://localhost:8083"; 


  getMinisters(): Observable<Minister[]> {
    return this.httpClient.get<Minister[]>(`${this.apiUrl}/ministers`);
  }

  getMinisterById(id: number): Observable<Minister> {
    return this.httpClient.get<Minister>(`${this.apiUrl}/minister/${id}`);
  }

  getMinisterByNaam(naam: string): Observable<Minister> {
    return this.httpClient.get<Minister>(`${this.apiUrl}/minister/naam/${naam}`);
  }

  postMinister(minister: Minister): Observable<Minister> {
    const token = this.authService.getIdToken();
    
    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
    return this.httpClient.post<Minister>(`${this.apiUrl}/minister/create`, minister, {headers: headers, });
    } else {
      console.error('No token found');
      return new Observable(); 
    }
  }

  putMinister(id: number, minister: Minister): Observable<Minister> {
    const token = this.authService.getIdToken();
    
    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
    return this.httpClient.put<Minister>(`${this.apiUrl}/minister/${id}/edit`, minister, {headers: headers, });
  } else {
    console.error('No token found');
    return new Observable(); 
  }

  }

  deleteMinister(id: number): Observable<Minister> {
    const token = this.authService.getIdToken();
    
    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
    return this.httpClient.delete<Minister>(`${this.apiUrl}/minister/${id}/delete`, {headers: headers, });
  } else {
    console.error('No token found');
    return new Observable(); 
  }
  }
}
