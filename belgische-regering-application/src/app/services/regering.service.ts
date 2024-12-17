import { Injectable } from '@angular/core';
import { Regering } from '../interfaces/regering';
import { Observable } from 'rxjs';

import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class RegeringService {

  constructor(private httpClient: HttpClient) { }

  getRegeringen(): Observable<Regering[]> {
    return this.httpClient.get<Regering[]>("http://localhost:8080/api/regering/all");
  }


}
