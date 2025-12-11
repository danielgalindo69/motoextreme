import { TestBed } from '@angular/core/testing';
import { AccesorioService } from './accesorios';

describe('Accesorios', () => {
  let service: AccesorioService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AccesorioService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
