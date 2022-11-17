import { TestBed } from '@angular/core/testing';

import { ContactListingService } from './contact-listing.service';

describe('ContactListingService', () => {
  let service: ContactListingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ContactListingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
