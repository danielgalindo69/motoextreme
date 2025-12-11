import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionModelos } from './gestion-modelos';

describe('GestionModelos', () => {
  let component: GestionModelos;
  let fixture: ComponentFixture<GestionModelos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GestionModelos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GestionModelos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
