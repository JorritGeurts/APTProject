import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegeringComponent } from './regering.component';

describe('RegeringComponent', () => {
  let component: RegeringComponent;
  let fixture: ComponentFixture<RegeringComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegeringComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegeringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
