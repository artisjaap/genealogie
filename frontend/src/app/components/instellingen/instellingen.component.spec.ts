import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstellingenComponent } from './instellingen.component';

describe('InstellingenComponent', () => {
  let component: InstellingenComponent;
  let fixture: ComponentFixture<InstellingenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InstellingenComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InstellingenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
