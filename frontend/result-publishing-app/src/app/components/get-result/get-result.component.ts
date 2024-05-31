import { Component } from '@angular/core';
import { Form, FormsModule } from '@angular/forms';
import { Marksheet } from '../../interfaces/marksheet';
import { ResultService } from '../../services/result.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-get-result',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './get-result.component.html',
  styleUrl: './get-result.component.css',
})
export class GetResultComponent {
  examination: string = 'SSC';
  year: string = '2023';
  rollNumber: string = '';

  marksheet: Marksheet | null = null;

  errorDetail: string = '';

  constructor(private resultService: ResultService) {}

  searchMarksheet(): void {
    this.resultService
      .getMarksheet(this.examination, this.year, this.rollNumber)
      .subscribe({
        next: (res) => {
          this.errorDetail = '';
          this.marksheet = res.data;
        },
        error: (err) => {
          if (err.error.status === 404) {
            this.marksheet = null;
            this.errorDetail = err.error.detail;
          }
        },
      });
  }

  reset(): void {
    this.marksheet = null;
    this.examination = 'SSC';
    this.year = '2023';
    this.rollNumber = '';
    this.errorDetail = '';
  }
}
