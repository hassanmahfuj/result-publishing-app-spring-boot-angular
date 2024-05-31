import { Component } from '@angular/core';
import { Result } from '../../interfaces/result';
import { RouterModule } from '@angular/router';
import { ResultService } from '../../services/result.service';
import { SuccessDetail } from '../../interfaces/success-detail';

@Component({
  selector: 'app-result',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './result.component.html',
  styleUrl: './result.component.css',
})
export class ResultComponent {
  results: Result[] = [];

  constructor(private resultService: ResultService) {}

  ngOnInit(): void {
    this.getResults();
  }

  getResults(): void {
    this.resultService.getResults().subscribe({
      next: (res: SuccessDetail) => {
        this.results = res.data;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  onDelete(resultId: number) {
    let yes: boolean = confirm('Are you sure to delete?');
    if (yes) {
      this.resultService.deleteResult(resultId).subscribe({
        next: (res: SuccessDetail) => {
          this.getResults();
        },
        error(err) {
          console.log(err);
        },
      });
    }
  }
}
