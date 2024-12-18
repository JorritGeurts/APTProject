import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartijlidListComponent } from './partijlid-list.component';

describe('PartijlidListComponent', () => {
  let component: PartijlidListComponent;
  let fixture: ComponentFixture<PartijlidListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartijlidListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartijlidListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
