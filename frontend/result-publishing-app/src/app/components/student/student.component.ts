import { Component } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { Student } from '../../interfaces/student';
import { Constants } from '../../constants';
import { RouterModule } from '@angular/router';
import { SuccessDetail } from '../../interfaces/success-detail';

@Component({
  selector: 'app-student',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './student.component.html',
  styleUrl: './student.component.css',
})
export class StudentComponent {
  host: string = Constants.API_HOST;
  students: Student[] = [];

  constructor(private studentService: StudentService) {}

  ngOnInit(): void {
    this.getStudents();
  }

  getStudents(): void {
    this.studentService.getStudents().subscribe({
      next: (res: SuccessDetail) => {
        this.students = res.data;
      },
      error(err) {
        console.log(err);
      },
    });
  }

  onDelete(studentId: number) {
    let yes: boolean = confirm('Are you sure to delete?');
    if (yes) {
      this.studentService.deleteStudent(studentId).subscribe({
        next: (res: SuccessDetail) => {
          this.getStudents();
        },
        error(err) {
          console.log(err);
        },
      });
    }
  }
}
