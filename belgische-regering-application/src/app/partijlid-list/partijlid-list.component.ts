import { Component, OnInit } from '@angular/core';
import { PartijlidService } from '../services/partijlid.service';
import { Partijlid } from '../interfaces/partijlid';
import { PartijlidComponent } from '../partijlid/partijlid.component';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-partijlid-list',
  standalone: true,
  imports: [PartijlidComponent, CommonModule],
  templateUrl: './partijlid-list.component.html',
  styleUrl: './partijlid-list.component.css'
})
export class PartijlidListComponent implements OnInit {
  partijleden$: Observable<Partijlid[]> = new Observable<Partijlid[]>();

  constructor(private partijlidService: PartijlidService) { }

  ngOnInit(): void {
    this.partijleden$ = this.partijlidService.getPartijleden();
  }
}
