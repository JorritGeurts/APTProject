import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MinisterCrudComponent } from './minister-crud.component';

describe('MinisterCrudComponent', () => {
  let component: MinisterCrudComponent;
  let fixture: ComponentFixture<MinisterCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MinisterCrudComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MinisterCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
