import { Injectable } from '@angular/core';
import { Partijlid } from '../interfaces/partijlid';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class PartijlidService {

  constructor(private httpClient: HttpClient) { }
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
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.post<Partijlid>(`${this.apiUrl}/partijlid/create`, partijlid, {headers: headers, });
  }

  putPartijlid(id: number, partijlid: Partijlid): Observable<Partijlid> {
    let headers = new HttpHeaders();
    headers = headers.set('Content-Type', 'application/json; charset=utf-8');
    return this.httpClient.put<Partijlid>(`${this.apiUrl}/partijlid/${id}/edit`, partijlid, {headers: headers, });

  }

  deletePartijlid(id: number): Observable<Partijlid> {
    return this.httpClient.delete<Partijlid>(`${this.apiUrl}/partijlid/${id}/delete`);
  }
}
