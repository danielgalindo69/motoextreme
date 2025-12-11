import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAccesorios } from './user-accesorios';

describe('UserAccesorios', () => {
  let component: UserAccesorios;
  let fixture: ComponentFixture<UserAccesorios>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserAccesorios]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserAccesorios);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
