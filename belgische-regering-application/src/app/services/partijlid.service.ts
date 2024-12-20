import { Injectable } from '@angular/core';
import { Partijlid } from '../interfaces/partijlid';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class PartijlidService {

  constructor(private httpClient: HttpClient) { }
  private apiUrl = "http://localhost:8081/api/partijlid"; 


  getPartijleden(): Observable<Partijlid[]> {
    return this.httpClient.get<Partijlid[]>(`${this.apiUrl}/all`);
  }

  getPartijlidById(id: number): Observable<Partijlid> {
    return this.httpClient.get<Partijlid>(`${this.apiUrl}/${id}`);
  }

  getPartijlidByNaam(naam: string): Observable<Partijlid> {
    return this.httpClient.get<Partijlid>(`${this.apiUrl}/naam/${naam}`);
  }

  postPartijlid(partijlid: Partijlid): Observable<Partijlid> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.post<Partijlid>(`${this.apiUrl}/create`, partijlid, {headers: headers, });
  }

  putPartijlid(id: number, partijlid: Partijlid): Observable<Partijlid> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.put<Partijlid>(`${this.apiUrl}/${id}/edit`, partijlid, {headers: headers, });

  }

  deletePartijlid(id: number): Observable<Partijlid> {
    return this.httpClient.delete<Partijlid>(`${this.apiUrl}/${id}/delete`);
  }
}