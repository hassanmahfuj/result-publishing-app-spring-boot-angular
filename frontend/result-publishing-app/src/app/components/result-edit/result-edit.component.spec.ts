import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultEditComponent } from './result-edit.component';

describe('ResultEditComponent', () => {
  let component: ResultEditComponent;
  let fixture: ComponentFixture<ResultEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResultEditComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ResultEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
