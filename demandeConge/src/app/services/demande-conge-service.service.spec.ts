import { TestBed } from '@angular/core/testing';

import { DemandeCongeServiceService } from './demande-conge-service.service';

describe('DemandeCongeServiceService', () => {
  let service: DemandeCongeServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DemandeCongeServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
