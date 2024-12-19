import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MinisterComponent } from './minister.component';

describe('MinisterComponent', () => {
  let component: MinisterComponent;
  let fixture: ComponentFixture<MinisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MinisterComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MinisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
