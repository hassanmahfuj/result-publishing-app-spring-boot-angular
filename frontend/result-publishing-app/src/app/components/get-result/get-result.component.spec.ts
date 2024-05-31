import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetResultComponent } from './get-result.component';

describe('GetResultComponent', () => {
  let component: GetResultComponent;
  let fixture: ComponentFixture<GetResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GetResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
