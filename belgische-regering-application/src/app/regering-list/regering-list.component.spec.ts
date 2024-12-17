import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegeringListComponent } from './regering-list.component';

describe('RegeringListComponent', () => {
  let component: RegeringListComponent;
  let fixture: ComponentFixture<RegeringListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegeringListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegeringListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
