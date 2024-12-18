import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartijlidFormComponent } from './partijlid-form.component';

describe('PartijlidFormComponent', () => {
  let component: PartijlidFormComponent;
  let fixture: ComponentFixture<PartijlidFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartijlidFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartijlidFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
