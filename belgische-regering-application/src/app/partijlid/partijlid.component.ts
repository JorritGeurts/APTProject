import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Partijlid } from '../interfaces/partijlid';
import { PartijlidService } from '../services/partijlid.service';

@Component({
  selector: 'app-partijlid',
  standalone: true,
  imports: [],
  templateUrl: './partijlid.component.html',
  styleUrl: './partijlid.component.css'
})
export class PartijlidComponent implements OnInit {
  @Input() partijlid!: Partijlid;
  @Output() partijlidDeleted = new EventEmitter<number>(); // Emit event to parent component

  constructor(private partijlidService: PartijlidService) { }

  ngOnInit(): void {
  }

  getSafeImageFilename(naam: string): string {
    return naam
      .toLowerCase()
      .replace(/[^a-z0-9]/g, '') + '.png'; // Remove non-alphanumeric chars and add .png
  }

  deletePartijlid(): void {
    if (!confirm(`Weet je zeker dat je ${this.partijlid.naam} wilt verwijderen?`)) return; // Confirm before delete

    this.partijlidService.deletePartijlid(this.partijlid.id.toString()).subscribe(
      () => {
        console.log(`Partijlid met id ${this.partijlid.id} is verwijderd.`);
        this.partijlidDeleted.emit(this.partijlid.id); // Notify parent component of deletion
      },
      (error) => {
        console.error('Fout bij het verwijderen van Partijlid:', error);
      }
    );
  }
}
