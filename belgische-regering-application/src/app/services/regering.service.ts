import { Injectable } from '@angular/core';
import { Regering } from '../interfaces/regering';
import { Observable } from 'rxjs';

import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class RegeringService {

   constructor(private httpClient: HttpClient) { }
      private apiUrl = "http://localhost:8080/api/regering"; 
    
    
      getRegeringen(): Observable<Regering[]> {
        return this.httpClient.get<Regering[]>(`${this.apiUrl}/all`);
      } 
    
      getRegeringByNaam(naam: string): Observable<Regering> {
          return this.httpClient.get<Regering>(`${this.apiUrl}/naam/${naam}`);
      }
}
