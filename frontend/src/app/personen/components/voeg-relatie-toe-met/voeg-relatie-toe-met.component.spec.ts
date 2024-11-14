import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VoegRelatieToeMetComponent } from './voeg-relatie-toe-met.component';

describe('VoegRelatieToeMetComponent', () => {
  let component: VoegRelatieToeMetComponent;
  let fixture: ComponentFixture<VoegRelatieToeMetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VoegRelatieToeMetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VoegRelatieToeMetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
