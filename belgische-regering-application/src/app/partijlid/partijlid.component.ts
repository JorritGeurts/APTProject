import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Partijlid } from '../interfaces/partijlid';

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

  constructor() { }

  ngOnInit(): void {
  }

  getSafeImageFilename(naam: string): string {
    return naam
      .toLowerCase()
      .replace(/[^a-z0-9]/g, '') + '.png'; // Remove non-alphanumeric chars and add .png
  }
}