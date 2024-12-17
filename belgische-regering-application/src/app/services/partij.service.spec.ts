import { TestBed } from '@angular/core/testing';

import { PartijService } from './partij.service';

describe('PartijService', () => {
  let service: PartijService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PartijService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
