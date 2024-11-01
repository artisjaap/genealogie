import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoTypeComponent } from './info-type.component';

describe('InfoTypeComponent', () => {
  let component: InfoTypeComponent;
  let fixture: ComponentFixture<InfoTypeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InfoTypeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InfoTypeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
