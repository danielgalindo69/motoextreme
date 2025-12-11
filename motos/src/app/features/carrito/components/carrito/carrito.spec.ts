import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CarritoService } from '../services';

describe('Carrito', () => {
  let component: CarritoService;
  let fixture: ComponentFixture<CarritoService>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarritoService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarritoService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
