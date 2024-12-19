import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MinisterListComponent } from './minister-list.component';

describe('MinisterListComponent', () => {
  let component: MinisterListComponent;
  let fixture: ComponentFixture<MinisterListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MinisterListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MinisterListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
