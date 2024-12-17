import { Injectable } from '@angular/core';
import { Partij } from '../interfaces/partij';
import { Observable } from 'rxjs';

import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root',
})
export class PartijService {

  constructor(private httpClient: HttpClient) { }

  getPartijen(): Observable<Partij[]> {
    return this.httpClient.get<Partij[]>("http://localhost:8083/alle-partijen");
  }


}
