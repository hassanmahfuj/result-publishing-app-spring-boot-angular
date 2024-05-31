import { Injectable } from '@angular/core';
import { Constants } from '../constants';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Student } from '../interfaces/student';

@Injectable({
  providedIn: 'root',
})
export class StudentService {
  private baseUrl: string = Constants.API_HOST + '/student';
  private fileUploadUrl: string = Constants.API_HOST + '/file-upload';

  constructor(private http: HttpClient) {}

  saveStudent(student: Student): Observable<any> {
    if (student.studentId) {
      return this.http.put<any>(this.baseUrl, student);
    }
    return this.http.post<any>(this.baseUrl, student);
  }

  getStudents(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  getStudent(studentId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${studentId}`);
  }

  deleteStudent(studentId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${studentId}`);
  }

  uploadStudentPhoto(formData: FormData): Observable<any> {
    return this.http.post<any>(this.fileUploadUrl, formData);
  }
}
