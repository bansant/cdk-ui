import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RodetailsComponent } from './rodetails.component';

describe('RodetailsComponent', () => {
  let component: RodetailsComponent;
  let fixture: ComponentFixture<RodetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RodetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RodetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
