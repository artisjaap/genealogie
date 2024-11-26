import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersoonReferenceComponent } from './persoon-reference.component';

describe('PersoonReferenceComponent', () => {
  let component: PersoonReferenceComponent;
  let fixture: ComponentFixture<PersoonReferenceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersoonReferenceComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersoonReferenceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
