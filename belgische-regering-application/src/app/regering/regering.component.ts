import { Component, Input, OnInit } from '@angular/core';
import { Regering } from '../interfaces/regering';

@Component({
  selector: 'app-regering',
  standalone: true,
  imports: [],
  templateUrl: './regering.component.html',
  styleUrl: './regering.component.css'
})
export class RegeringComponent implements OnInit {
  @Input() regering!: Regering;

  constructor() { }

  ngOnInit(): void {
  }

  getSafeImageFilename(naam: string): string {
    return naam
      .toLowerCase()
      .replace(/[^a-z0-9]/g, '') + '.png'; // Remove non-alphanumeric chars and add .png
  }
}
