import { Injectable } from '@angular/core';
import { Partij } from '../interfaces/partij';
import { Observable } from 'rxjs';

import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class PartijService {

  constructor(private httpClient: HttpClient) { }
    private apiUrl = "http://localhost:8083"; 
  
  
    getPartijen(): Observable<Partij[]> {
      return this.httpClient.get<Partij[]>(`${this.apiUrl}/partijen`);
    } 
  
    getPartijByNaam(naam: string): Observable<Partij> {
        return this.httpClient.get<Partij>(`${this.apiUrl}/partij/naam/${naam}`);
    }
}
