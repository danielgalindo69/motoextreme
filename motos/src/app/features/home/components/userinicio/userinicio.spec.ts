import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UserInicio } from './userinicio';

describe('Userinicio', () => {
  let component: UserInicio;
  let fixture: ComponentFixture<UserInicio>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserInicio]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserInicio);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
