import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistreerComponent } from './registreer.component';

describe('RegistreerComponent', () => {
  let component: RegistreerComponent;
  let fixture: ComponentFixture<RegistreerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegistreerComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegistreerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
