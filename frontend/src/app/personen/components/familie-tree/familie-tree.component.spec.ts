import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FamilieTreeComponent } from './familie-tree.component';

describe('FamilieTreeComponent', () => {
  let component: FamilieTreeComponent;
  let fixture: ComponentFixture<FamilieTreeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FamilieTreeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FamilieTreeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
