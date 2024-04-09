import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenRoDetailsComponent } from './open-ro-details.component';

describe('OpenRoDetailsComponent', () => {
  let component: OpenRoDetailsComponent;
  let fixture: ComponentFixture<OpenRoDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpenRoDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenRoDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
