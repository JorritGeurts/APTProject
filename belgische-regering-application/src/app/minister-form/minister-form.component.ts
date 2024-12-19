import { Component, OnInit } from '@angular/core';
import { Minister } from '../interfaces/minister';
import { Router } from '@angular/router';
import { MinisterService } from '../services/minister.service';
import { FormsModule } from '@angular/forms';
import { PartijlidService } from '../services/partijlid.service';
import { RegeringService } from '../services/regering.service';
import { Regering } from '../interfaces/regering';
import { Partijlid } from '../interfaces/partijlid';
import { CommonModule } from '@angular/common';
import { PartijlidComponent } from '../partijlid/partijlid.component';
import { RegeringComponent } from '../regering/regering.component';

@Component({
  selector: 'app-minister-form',
  standalone: true,
  imports: [FormsModule, CommonModule, PartijlidComponent, RegeringComponent],
  templateUrl: './minister-form.component.html',
  styleUrl: './minister-form.component.css'
})
export class MinisterFormComponent implements OnInit {
  isAdd: boolean = false;
  isEdit: boolean = false;
  ministerId: number = 0;
  partijleden$: Partijlid[] = [];
  regeringen$: Regering[] = [];

  minister: Minister = {
    id: 0,
    naam: '',
    partijlidNaam: '',
    regeringNaam: ''
  };

  isSubmitted: boolean = false;
  errorMessage: string = "";

  constructor(private router: Router, private ministerService: MinisterService, private partijlidService: PartijlidService, 
    private regeringService: RegeringService
  ) {
    const state = this.router.getCurrentNavigation()?.extras.state || {};
    this.isAdd = state['mode'] === 'add';
    this.isEdit = state['mode'] === 'edit';
    this.ministerId = +state['id'];

    if (!this.isAdd && !this.isEdit) {
      this.isAdd = true;
    }

    if (this.ministerId != null && this.ministerId > 0) {
      this.ministerService.getMinisterById(this.ministerId).subscribe(result => {
        this.minister = result;
      });
    } 
  }

  ngOnInit(): void {
    this.partijlidService.getPartijleden().subscribe((partijleden) => {
      this.partijleden$ = partijleden;
      console.log(this.partijleden$);
    });

    this.regeringService.getRegeringen().subscribe((regeringen) => {
      this.regeringen$ = regeringen;
    });
  }

  onSubmit() {
    this.isSubmitted = true;
    if (this.isAdd) {
      this.ministerService.postMinister(this.minister).subscribe({
        next: () => {
          window.alert('Minister succesvol toegevoegd!');
          this.router.navigateByUrl("/admin/ministers");
        },
        error: (e) => this.errorMessage = e.message
      });
    }
    if (this.isEdit) {
      this.ministerService.putMinister(this.ministerId, this.minister).subscribe({
        next: () => {
          window.alert('Minister succesvol geüpdate!');
          this.router.navigateByUrl("/admin/ministers");
        },
        error: (e) => this.errorMessage = e.message
      });
    }
  }
}
