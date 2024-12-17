import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartijComponent } from './partij.component';

describe('PartijComponent', () => {
  let component: PartijComponent;
  let fixture: ComponentFixture<PartijComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartijComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartijComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
