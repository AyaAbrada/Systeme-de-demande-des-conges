import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoriqueEmployeComponent } from './historique-employe.component';

describe('HistoriqueEmployeComponent', () => {
  let component: HistoriqueEmployeComponent;
  let fixture: ComponentFixture<HistoriqueEmployeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HistoriqueEmployeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoriqueEmployeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
