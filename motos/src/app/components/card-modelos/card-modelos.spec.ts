import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardModelos } from './card-modelos';

describe('CardModelos', () => {
  let component: CardModelos;
  let fixture: ComponentFixture<CardModelos>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CardModelos]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CardModelos);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
