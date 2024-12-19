import { Component, OnInit } from '@angular/core';
import { PartijlidService } from '../services/partijlid.service';
import { Observable } from 'rxjs';
import { Partijlid } from '../interfaces/partijlid';
import { Router } from '@angular/router';
import { AsyncPipe } from '@angular/common';

@Component({
  selector: 'app-partijlid-crud',
  standalone: true,
  imports: [AsyncPipe],
  templateUrl: './partijlid-crud.component.html',
  styleUrl: './partijlid-crud.component.css'
})
export class PartijlidCrudComponent implements OnInit {
  partijleden$!: Observable<Partijlid[]>;
  errorMessage: string = '';

  constructor(private partijlidService: PartijlidService, private router: Router) {
  }

  ngOnInit(): void {
    this.getPartijleden();
  }

  getPartijleden() {
    this.partijleden$ = this.partijlidService.getPartijleden();
  }

  add() {
    //Navigate to form in add mode
    this.router.navigate(['admin/partijlid/form'], { state: { mode: 'add' } });
  }

  edit(id: number) {
    //Navigate to form in edit mode
    this.router.navigate(['admin/partijlid/form'], { state: { id: id, mode: 'edit' } });
  }

  delete(id: number, naam: string) {
    const isConfirmed = window.confirm(`Weet je zeker dat je ${naam} wilt verwijderen?`);
    if (isConfirmed) {
      this.partijlidService.deletePartijlid(id).subscribe({
        next: () => this.getPartijleden(),
        error: (e) => this.errorMessage = e.message
      });
    }
  }
}

