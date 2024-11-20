import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdatePersoonsgegevensComponent } from './update-persoonsgegevens.component';

describe('UpdatePersoonsgegevensComponent', () => {
  let component: UpdatePersoonsgegevensComponent;
  let fixture: ComponentFixture<UpdatePersoonsgegevensComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdatePersoonsgegevensComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdatePersoonsgegevensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
