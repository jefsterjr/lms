import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AlertService} from '../alert/alert.service';
import {Course} from '../models/course';
import {AppService} from '../app.service';
import {Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class CourseService extends AppService {

  courseApiUrl = '/api/courses';

  constructor(
    private http: HttpClient,
    protected alertService: AlertService
  ) {
    super(alertService);
  }

  createCourse(course: Course): Observable<void> {
    return this.http.post(this.courseApiUrl, course)
    .pipe(
      catchError(err => this.handleError(err))
    );
  }

  listCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.courseApiUrl)
    .pipe(
      catchError(err => this.handleError(err))
    );
  }

  findCourse(id: number): Observable<Course> {
    return this.http.get<Course>(`${this.courseApiUrl}/${id}`)
    .pipe(
      catchError(err => this.handleError(err))
    );
  }

  updateCourse(id: number, course: Course): Observable<void> {
    return this.http.put(`${this.courseApiUrl}/${id}`, course)
    .pipe(
      catchError(err => this.handleError(err))
    );
  }

  deleteCourse(id: number | undefined): Observable<void> {
    return this.http.delete(`${this.courseApiUrl}/${id}`)
    .pipe(
      catchError(err => this.handleError(err))
    );
  }
}
