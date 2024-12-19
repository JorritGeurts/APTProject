import { Component, Input, OnInit, Output, EventEmitter } from '@angular/core';
import { Minister } from '../interfaces/minister';

@Component({
  selector: 'app-minister',
  standalone: true,
  imports: [],
  templateUrl: './minister.component.html',
  styleUrl: './minister.component.css'
})
export class MinisterComponent implements OnInit {
  @Input() minister!: Minister;
  @Output() ministerDeleted = new EventEmitter<number>(); // Emit event to parent component

  constructor() { }

  ngOnInit(): void {
  }

  getSafeImageFilename(naam: string): string {
    return naam
      .toLowerCase()
      .replace(/[^a-z0-9]/g, '') + '.png'; // Remove non-alphanumeric chars and add .png
  }
}