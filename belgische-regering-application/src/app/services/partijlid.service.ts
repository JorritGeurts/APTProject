import { Injectable } from '@angular/core';
import { Partijlid } from '../interfaces/partijlid';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { AuthGoogleService } from './auth-google.service';

@Injectable({
  providedIn: 'root',
})
export class PartijlidService {

  constructor(private httpClient: HttpClient, private authService: AuthGoogleService) { }
  private apiUrl = "http://localhost:8083"; 


  getPartijleden(): Observable<Partijlid[]> {
    return this.httpClient.get<Partijlid[]>(`${this.apiUrl}/partijleden`);
  }

  getPartijlidById(id: number): Observable<Partijlid> {
    return this.httpClient.get<Partijlid>(`${this.apiUrl}/partijlid/${id}`);
  }

  getPartijlidByNaam(naam: string): Observable<Partijlid> {
    return this.httpClient.get<Partijlid>(`${this.apiUrl}/partijlid/naam/${naam}`);
  }

  postPartijlid(partijlid: Partijlid): Observable<Partijlid> {
    const token = this.authService.getIdToken();

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
      return this.httpClient.post<Partijlid>(`${this.apiUrl}/partijlid/create`, partijlid, {headers: headers});
    } else {
      console.error('No token found');
      return new Observable(); 
    }
  }

  putPartijlid(id: number, partijlid: Partijlid): Observable<Partijlid> {
    const token = this.authService.getIdToken();

    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
    return this.httpClient.put<Partijlid>(`${this.apiUrl}/partijlid/${id}/edit`, partijlid, {headers: headers, });
  } else {
    console.error('No token found');
    return new Observable(); 
  }

  }

  deletePartijlid(id: number): Observable<Partijlid> {
    const token = this.authService.getIdToken();
    
    if (token) {
      const headers = new HttpHeaders({
        'Authorization': `Bearer ${token}`
      });
    return this.httpClient.delete<Partijlid>(`${this.apiUrl}/partijlid/${id}/delete`, {headers: headers, });
    } else {
      console.error('No token found');
      return new Observable(); 
    }
  }
}
