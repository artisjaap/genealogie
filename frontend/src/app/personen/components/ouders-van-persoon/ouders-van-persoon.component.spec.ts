import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OudersVanPersoonComponent } from './ouders-van-persoon.component';

describe('OudersVanPersoonComponent', () => {
  let component: OudersVanPersoonComponent;
  let fixture: ComponentFixture<OudersVanPersoonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OudersVanPersoonComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OudersVanPersoonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
