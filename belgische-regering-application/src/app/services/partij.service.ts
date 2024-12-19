import { Injectable } from '@angular/core';
import { Partij } from '../interfaces/partij';
import { Observable } from 'rxjs';

import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class PartijService {

  constructor(private httpClient: HttpClient) { }
    private apiUrl = "http://localhost:8082/api/partij"; 
  
  
    getPartijen(): Observable<Partij[]> {
      return this.httpClient.get<Partij[]>(`${this.apiUrl}/all`);
    } 
  
    getPartijByNaam(naam: string): Observable<Partij> {
        return this.httpClient.get<Partij>(`${this.apiUrl}/naam/${naam}`);
    }
}
