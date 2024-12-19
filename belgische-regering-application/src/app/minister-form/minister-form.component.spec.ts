import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MinisterFormComponent } from './minister-form.component';

describe('MinisterFormComponent', () => {
  let component: MinisterFormComponent;
  let fixture: ComponentFixture<MinisterFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MinisterFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MinisterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
