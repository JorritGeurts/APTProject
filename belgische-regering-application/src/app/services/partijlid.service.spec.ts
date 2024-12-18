import { TestBed } from '@angular/core/testing';

import { PartijlidService } from './partijlid.service';

describe('PartijlidService', () => {
  let service: PartijlidService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PartijlidService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
