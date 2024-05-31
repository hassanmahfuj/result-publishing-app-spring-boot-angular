import { Injectable } from '@angular/core';
import { Constants } from '../constants';
import { HttpClient } from '@angular/common/http';
import { Result } from '../interfaces/result';
import { Observable } from 'rxjs';
import { SuccessDetail } from '../interfaces/success-detail';

@Injectable({
  providedIn: 'root',
})
export class ResultService {
  private baseUrl: string = Constants.API_HOST + '/result';

  constructor(private http: HttpClient) {}

  saveResult(result: Result): Observable<any> {
    return this.http.post<any>(this.baseUrl, result);
  }

  getResults(): Observable<any> {
    return this.http.get<any>(this.baseUrl);
  }

  getResult(resultId: number): Observable<SuccessDetail> {
    return this.http.get<SuccessDetail>(`${this.baseUrl}/${resultId}`);
  }

  deleteResult(resultId: number): Observable<any> {
    return this.http.delete<any>(`${this.baseUrl}/${resultId}`);
  }

  getMarksheet(
    examination: string,
    year: string,
    rollNumber: string
  ): Observable<SuccessDetail> {
    return this.http.get<SuccessDetail>(`${this.baseUrl}/marksheet`, {
      params: { examination, year, rollNumber },
    });
  }
}
