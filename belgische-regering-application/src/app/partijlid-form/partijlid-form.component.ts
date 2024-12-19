import { Component, OnInit } from '@angular/core';
import { Partijlid } from '../interfaces/partijlid';
import { Router } from '@angular/router';
import { PartijlidService } from '../services/partijlid.service';
import { FormsModule } from '@angular/forms';
import { PartijService } from '../services/partij.service';
import { RegeringService } from '../services/regering.service';
import { Regering } from '../interfaces/regering';
import { Partij } from '../interfaces/partij';
import { CommonModule } from '@angular/common';
import { PartijComponent } from '../partij/partij.component';
import { RegeringComponent } from '../regering/regering.component';

@Component({
  selector: 'app-partijlid-form',
  standalone: true,
  imports: [FormsModule, CommonModule, PartijComponent, RegeringComponent],
  templateUrl: './partijlid-form.component.html',
  styleUrl: './partijlid-form.component.css'
})
export class PartijlidFormComponent implements OnInit {
  isAdd: boolean = false;
  isEdit: boolean = false;
  partijlidId: number = 0;
  partijen$: Partij[] = [];
  regeringen$: Regering[] = [];

  partijlid: Partijlid = {
    id: 0, 
    naam: '',
    email: '',
    partijNaam: '',
    regeringNaam: ''
  };

  isSubmitted: boolean = false;
  errorMessage: string = "";

  constructor(private router: Router, private partijlidService: PartijlidService, private partijService: PartijService, 
    private regeringService: RegeringService
  ) {
    const state = this.router.getCurrentNavigation()?.extras.state || {};
    this.isAdd = state['mode'] === 'add';
    this.isEdit = state['mode'] === 'edit';
    this.partijlidId = +state['id'];

    if (!this.isAdd && !this.isEdit) {
      this.isAdd = true;
    }

    if (this.partijlidId != null && this.partijlidId > 0) {
      this.partijlidService.getPartijlidById(this.partijlidId).subscribe(result => {
        this.partijlid = result;
      });
    } 
  }

  ngOnInit(): void {
    this.partijService.getPartijen().subscribe((partijen) => {
      this.partijen$ = partijen;
      console.log(this.partijen$);
    });

    this.regeringService.getRegeringen().subscribe((regeringen) => {
      this.regeringen$ = regeringen;
    });
  }

  onSubmit() {
    this.isSubmitted = true;
    if (this.isAdd) {
      this.partijlidService.postPartijlid(this.partijlid).subscribe({
        next: () => {
          window.alert('Partijlid succesvol toegevoegd!');
          this.router.navigateByUrl("/admin/partijleden");
        },
        error: (e) => this.errorMessage = e.message
      });
    }
    if (this.isEdit) {
      this.partijlidService.putPartijlid(this.partijlidId, this.partijlid).subscribe({
        next: () => {
          window.alert('Partijlid succesvol geüpdate!');
          this.router.navigateByUrl("/admin/partijleden");
        },
        error: (e) => this.errorMessage = e.message
      });
    }
  }
}
