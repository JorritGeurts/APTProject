import { Component, OnInit } from '@angular/core';
import { PartijService } from '../services/partij.service';
import { Partij } from '../interfaces/partij';
import { PartijComponent } from '../partij/partij.component';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-partij-list',
  standalone: true,
  imports: [PartijComponent, CommonModule],
  templateUrl: './partij-list.component.html',
  styleUrl: './partij-list.component.css'
})
export class PartijListComponent implements OnInit {
  partijen$: Observable<Partij[]> = new Observable<Partij[]>();

  constructor(private partijService: PartijService) { }

  ngOnInit(): void {
    this.partijen$ = this.partijService.getPartijen();
  }
}
