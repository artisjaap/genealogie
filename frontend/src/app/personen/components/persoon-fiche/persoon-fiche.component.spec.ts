import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersoonFicheComponent } from './persoon-fiche.component';

describe('PersoonFicheComponent', () => {
  let component: PersoonFicheComponent;
  let fixture: ComponentFixture<PersoonFicheComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersoonFicheComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PersoonFicheComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
