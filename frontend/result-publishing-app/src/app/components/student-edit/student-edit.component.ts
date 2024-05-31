import { Component } from '@angular/core';
import { Student } from '../../interfaces/student';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../../services/student.service';
import { FormsModule } from '@angular/forms';
import { Constants } from '../../constants';
import { SuccessDetail } from '../../interfaces/success-detail';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-student-edit',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './student-edit.component.html',
  styleUrl: './student-edit.component.css',
})
export class StudentEditComponent {
  host: string = Constants.API_HOST;
  student: Student = {
    studentId: 0,
    name: '',
    fatherName: '',
    motherName: '',
    dateOfBirth: new Date().toISOString().split('T')[0],
    imageUrl: '',
  };
  imageSrc: string | ArrayBuffer | null = null;
  imageFile: File | null = null;
  formErrors: any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private studentService: StudentService
  ) {}

  ngOnInit(): void {
    this.student.studentId = this.route.snapshot.params['id'];
    if (this.student.studentId) {
      this.studentService.getStudent(this.student.studentId).subscribe({
        next: (res: SuccessDetail) => {
          this.student = res.data;
          if (this.student.imageUrl !== '')
            this.imageSrc = this.host + this.student.imageUrl;
        },
        error: (err) => {
          this.router.navigate(['admin/student']);
        },
      });
    }
  }

  onImageSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.imageSrc = reader.result;
      };
      reader.readAsDataURL(file);
      this.imageFile = file;
    }
  }

  onSaveWithPhoto() {
    this.formErrors = {};
    if (this.imageFile) {
      const formData: FormData = new FormData();
      formData.append('file', this.imageFile!);
      this.studentService.uploadStudentPhoto(formData).subscribe((res) => {
        this.student.imageUrl = res.data;
        this.imageFile = null;
        this.saveStudent();
      });
    } else if (this.student.imageUrl !== '') {
      this.saveStudent();
    } else {
      this.formErrors = { image: 'Select an image' };
    }
  }

  saveStudent() {
    this.studentService.saveStudent(this.student).subscribe({
      next: (res) => {
        this.router.navigate(['admin/student']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.status === 400) {
          this.formErrors = err.error.formErrors;
        }
      },
    });
  }
}
