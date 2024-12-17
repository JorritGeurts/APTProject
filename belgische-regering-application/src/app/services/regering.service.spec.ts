import { TestBed } from '@angular/core/testing';

import { RegeringService } from './regering.service';

describe('RegeringService', () => {
  let service: RegeringService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RegeringService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
