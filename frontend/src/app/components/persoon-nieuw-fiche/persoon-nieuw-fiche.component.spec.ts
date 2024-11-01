import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersoonNieuwFicheComponent } from './persoon-nieuw-fiche.component';

describe('PersoonNieuwFicheComponent', () => {
  let component: PersoonNieuwFicheComponent;
  let fixture: ComponentFixture<PersoonNieuwFicheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersoonNieuwFicheComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PersoonNieuwFicheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
