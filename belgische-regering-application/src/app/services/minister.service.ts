import { Injectable } from '@angular/core';
import { Minister } from '../interfaces/minister';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class MinisterService {

  constructor(private httpClient: HttpClient) { }
  private apiUrl = "http://localhost:8084/api/minister"; 


  getMinisters(): Observable<Minister[]> {
    return this.httpClient.get<Minister[]>(`${this.apiUrl}/all`);
  }

  getMinisterById(id: number): Observable<Minister> {
    return this.httpClient.get<Minister>(`${this.apiUrl}/${id}`);
  }

  getMinisterByNaam(naam: string): Observable<Minister> {
    return this.httpClient.get<Minister>(`${this.apiUrl}/naam/${naam}`);
  }

  postMinister(minister: Minister): Observable<Minister> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.post<Minister>(`${this.apiUrl}/create`, minister, {headers: headers, });
  }

  putMinister(id: number, minister: Minister): Observable<Minister> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.put<Minister>(`${this.apiUrl}/${id}/edit`, minister, {headers: headers, });

  }

  deleteMinister(id: number): Observable<Minister> {
    return this.httpClient.delete<Minister>(`${this.apiUrl}/${id}/delete`);
  }
}
