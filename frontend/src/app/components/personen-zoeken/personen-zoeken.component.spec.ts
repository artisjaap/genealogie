import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonenZoekenComponent } from './personen-zoeken.component';

describe('PersonenZoekenComponent', () => {
  let component: PersonenZoekenComponent;
  let fixture: ComponentFixture<PersonenZoekenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonenZoekenComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PersonenZoekenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
