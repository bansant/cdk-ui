import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoDetailPopoverComponent } from './ro-detail-popover.component';

describe('RoDetailPopoverComponent', () => {
  let component: RoDetailPopoverComponent;
  let fixture: ComponentFixture<RoDetailPopoverComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoDetailPopoverComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoDetailPopoverComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
