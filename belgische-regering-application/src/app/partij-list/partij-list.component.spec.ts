import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartijListComponent } from './partij-list.component';

describe('PartijListComponent', () => {
  let component: PartijListComponent;
  let fixture: ComponentFixture<PartijListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartijListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartijListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
