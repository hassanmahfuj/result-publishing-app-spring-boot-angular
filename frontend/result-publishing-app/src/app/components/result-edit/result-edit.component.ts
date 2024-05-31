import { Component } from '@angular/core';
import { StudentService } from '../../services/student.service';
import { Student } from '../../interfaces/student';
import {
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
} from '@angular/forms';
import { Result } from '../../interfaces/result';
import { Subject } from '../../interfaces/subject';
import { CommonModule } from '@angular/common';
import { ResultService } from '../../services/result.service';
import { ActivatedRoute, Router } from '@angular/router';
import { SuccessDetail } from '../../interfaces/success-detail';

@Component({
  selector: 'app-result-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './result-edit.component.html',
  styleUrl: './result-edit.component.css',
})
export class ResultEditComponent {
  students: Student[] = [];
  resultForm: FormGroup;
  resultId: number | null = null;
  formErrors: any;

  constructor(
    private studentService: StudentService,
    private resultService: ResultService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.resultForm = this.createResultForm();
  }

  ngOnInit(): void {
    this.studentService.getStudents().subscribe({
      next: (res: SuccessDetail) => {
        this.students = res.data;
        this.resultForm.patchValue({
          studentId: this.students.length > 0 ? this.students[0].studentId : '',
        });
      },
      error: (err) => {
        console.log(err);
        this.router.navigate(['admin/result']);
      },
    });

    this.resultId = this.route.snapshot.params['id'];
    if (this.resultId) {
      this.resultService.getResult(this.resultId).subscribe({
        next: (res) => {
          this.loadDataFromResponse(res.data);
        },
        error: (err) => {
          console.log(err);
          this.router.navigate(['admin/result']);
        },
      });
    }
  }

  createResultForm(): FormGroup {
    return this.formBuilder.group({
      studentId: [''],
      resultId: [''],
      rollNumber: [''],
      gpa: [''],
      year: [''],
      groupName: [''],
      examination: [''],
      subjects: this.formBuilder.array([]),
    });
  }

  createSubjectGroup(subject?: Subject): FormGroup {
    return this.formBuilder.group({
      subjectId: [subject?.subjectId || ''],
      code: [subject?.code || ''],
      name: [subject?.name || ''],
      gpa: [subject?.gpa || ''],
    });
  }

  get subjects() {
    return (this.resultForm.get('subjects') as FormArray).controls;
  }

  addSubject() {
    const subjects = this.resultForm.get('subjects') as FormArray;
    subjects.push(this.createSubjectGroup());
  }

  removeSubject(index: number) {
    const subjects = this.resultForm.get('subjects') as FormArray;
    subjects.removeAt(index);
  }

  loadDataFromResponse(result: Result): void {
    this.resultForm.patchValue(result);

    const subjects = this.resultForm.get('subjects') as FormArray;
    result.subjects.forEach((subject) => {
      subjects.push(this.createSubjectGroup(subject));
    });

    this.resultForm.get('studentId')?.disable();
  }

  onSubmit() {
    this.formErrors = {};
    // const formValues = this.resultForm.value;
    const formValues = this.resultForm.getRawValue();
    const result: Result = formValues as Result;

    let errors = false;
    result.subjects.forEach((item, index) => {
      if (item.code.toString().trim() === '') {
        this.formErrors['code' + index] = 'Code cannot be null';
        errors = true;
      }
      if (item.name.trim() === '') {
        this.formErrors['name' + index] = 'Subject Name is required';
        errors = true;
      }
      if (item.gpa.toString().trim() === '') {
        this.formErrors['gpa' + index] = 'GPA cannot be null';
        errors = true;
      }
    });
    if (errors) return;

    this.resultService.saveResult(result).subscribe({
      next: (res) => {
        this.router.navigate(['admin/result']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.status === 400) {
          this.formErrors = err.error.formErrors;
        } else if (err.error.status === 409) {
          this.formErrors['duplicate'] = err.error.title;
        }
      },
    });
  }
}
