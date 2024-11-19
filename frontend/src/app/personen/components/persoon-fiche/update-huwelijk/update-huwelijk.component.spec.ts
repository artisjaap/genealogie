import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateHuwelijkComponent } from './update-huwelijk.component';

describe('UpdateHuwelijkComponent', () => {
  let component: UpdateHuwelijkComponent;
  let fixture: ComponentFixture<UpdateHuwelijkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateHuwelijkComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateHuwelijkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
