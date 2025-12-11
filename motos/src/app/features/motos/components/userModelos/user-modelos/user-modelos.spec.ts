import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserModelos } from './user-modelos';

describe('UserModelos', () => {
  let component: UserModelos;
  let fixture: ComponentFixture<UserModelos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserModelos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserModelos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
