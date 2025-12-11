import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MotoService } from '../../service/modelos';

describe('Modelos', () => {
  let component: MotoService;
  let fixture: ComponentFixture<MotoService>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MotoService]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MotoService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
