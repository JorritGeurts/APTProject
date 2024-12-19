import { Component, OnInit } from '@angular/core';
import { MinisterService } from '../services/minister.service';
import { Minister } from '../interfaces/minister';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';
import { MinisterComponent } from '../minister/minister.component';

@Component({
  selector: 'app-minister-list',
  standalone: true,
  imports: [MinisterComponent, CommonModule],
  templateUrl: './minister-list.component.html',
  styleUrl: './minister-list.component.css'
})
export class MinisterListComponent implements OnInit {
  ministers$: Observable<Minister[]> = new Observable<Minister[]>();

  constructor(private ministerService: MinisterService) { }

  ngOnInit(): void {
    this.ministers$ = this.ministerService.getMinisters();
  }
}
