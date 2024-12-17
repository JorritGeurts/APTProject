import { Component, Input, OnInit } from '@angular/core';
import { Partij } from '../interfaces/partij';

@Component({
  selector: 'app-partij',
  standalone: true,
  imports: [],
  templateUrl: './partij.component.html',
  styleUrl: './partij.component.css'
})
export class PartijComponent implements OnInit {
  @Input() partij!: Partij;

  constructor() { }

  ngOnInit(): void {
  }

  getSafeImageFilename(naam: string): string {
    return naam
      .toLowerCase()
      .replace(/[^a-z0-9]/g, '') + '.png'; // Remove non-alphanumeric chars and add .png
  }
}
