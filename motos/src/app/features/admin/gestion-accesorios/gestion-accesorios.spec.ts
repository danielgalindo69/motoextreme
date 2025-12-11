import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionAccesorios } from './gestion-accesorios';

describe('GestionAccesorios', () => {
  let component: GestionAccesorios;
  let fixture: ComponentFixture<GestionAccesorios>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestionAccesorios]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionAccesorios);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
