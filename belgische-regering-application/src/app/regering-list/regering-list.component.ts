import { Component, OnInit } from '@angular/core';
import { RegeringService } from '../services/regering.service';
import { Regering } from '../interfaces/regering';
import { RegeringComponent } from '../regering/regering.component';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-regering-list',
  standalone: true,
  imports: [RegeringComponent, CommonModule],
  templateUrl: './regering-list.component.html',
  styleUrl: './regering-list.component.css'
})
export class RegeringListComponent implements OnInit {
  regeringen$: Observable<Regering[]> = new Observable<Regering[]>();

  constructor(private regeringService: RegeringService) { }

  ngOnInit(): void {
    this.regeringen$ = this.regeringService.getRegeringen();
  }
}
