import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CourseEnrollmentsService {

  private baseUrl = '/api/enrolled-courses';

  constructor(private http: HttpClient) {
  }

  getCourseEnrollment(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  deleteCourseEnrollment(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  listStudentCourseEnrollments(studentId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/student/${studentId}`);
  }

}
