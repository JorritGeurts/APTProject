import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartijlidCrudComponent } from './partijlid-crud.component';

describe('PartijlidCrudComponent', () => {
  let component: PartijlidCrudComponent;
  let fixture: ComponentFixture<PartijlidCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PartijlidCrudComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PartijlidCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
