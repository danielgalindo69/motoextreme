import { TestBed } from '@angular/core/testing';

import { Modelos } from './modelos';

describe('Modelos', () => {
  let service: Modelos;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Modelos);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
