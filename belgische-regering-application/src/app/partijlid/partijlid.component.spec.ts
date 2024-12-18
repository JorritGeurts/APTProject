import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartijlidComponent } from './partijlid.component';

describe('PartijlidComponent', () => {
  let component: PartijlidComponent;
  let fixture: ComponentFixture<PartijlidComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartijlidComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartijlidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
