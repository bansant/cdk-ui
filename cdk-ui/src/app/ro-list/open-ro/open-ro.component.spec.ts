import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OpenRoComponent } from './open-ro.component';

describe('OpenRoComponent', () => {
  let component: OpenRoComponent;
  let fixture: ComponentFixture<OpenRoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OpenRoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OpenRoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
