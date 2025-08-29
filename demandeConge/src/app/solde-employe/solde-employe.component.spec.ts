import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SoldeEmployeComponent } from './solde-employe.component';

describe('SoldeEmployeComponent', () => {
  let component: SoldeEmployeComponent;
  let fixture: ComponentFixture<SoldeEmployeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SoldeEmployeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SoldeEmployeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
