import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClosedRoComponent } from './closed-ro.component';

describe('ClosedRoComponent', () => {
  let component: ClosedRoComponent;
  let fixture: ComponentFixture<ClosedRoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClosedRoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClosedRoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
