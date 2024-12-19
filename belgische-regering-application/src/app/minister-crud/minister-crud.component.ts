import { Component, OnInit } from '@angular/core';
import { MinisterService } from '../services/minister.service';
import { Observable } from 'rxjs';
import { Minister } from '../interfaces/minister';
import { Router } from '@angular/router';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-minister-crud',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './minister-crud.component.html',
  styleUrl: './minister-crud.component.css'
})
export class MinisterCrudComponent implements OnInit {
  ministers$!: Observable<Minister[]>;
  errorMessage: string = '';

  constructor(private ministerService: MinisterService, private router: Router) {
  }

  ngOnInit(): void {
    this.getMinisters();
  }

  getMinisters() {
    this.ministers$ = this.ministerService.getMinisters();
  }

  add() {
    //Navigate to form in add mode
    this.router.navigate(['admin/minister/form'], { state: { mode: 'add' } });
  }

  edit(id: number) {
    //Navigate to form in edit mode
    this.router.navigate(['admin/minister/form'], { state: { id: id, mode: 'edit' } });
  }

  delete(id: number, naam: string) {
    const isConfirmed = window.confirm(`Weet je zeker dat je ${naam} wilt verwijderen?`);
    if (isConfirmed) {
      this.ministerService.deleteMinister(id).subscribe({
        next: () => this.getMinisters(),
        error: (e) => this.errorMessage = e.message
      });
    }
  }
}

