import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersoonInfoFormComponent } from './persoon-info-form.component';

describe('PersoonInfoFormComponent', () => {
  let component: PersoonInfoFormComponent;
  let fixture: ComponentFixture<PersoonInfoFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersoonInfoFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PersoonInfoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
