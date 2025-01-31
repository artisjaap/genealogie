import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GebruikersComponent } from './gebruikers.component';

describe('GebruikersComponent', () => {
  let component: GebruikersComponent;
  let fixture: ComponentFixture<GebruikersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GebruikersComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GebruikersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
