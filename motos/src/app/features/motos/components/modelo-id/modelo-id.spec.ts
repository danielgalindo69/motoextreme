import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MotoDetalleComponent } from './modelo-id';

describe('ModeloID', () => {
  let component: MotoDetalleComponent;
  let fixture: ComponentFixture<MotoDetalleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MotoDetalleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MotoDetalleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
