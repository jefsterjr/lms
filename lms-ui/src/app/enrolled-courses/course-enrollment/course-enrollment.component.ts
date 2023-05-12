import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Course} from '../../models/course';
import {CourseService} from '../../course/course.service';
import {Router} from '@angular/router';
import {CourseEnrollment} from '../../models/course-enrollment';
import {CourseEnrollmentsService} from '../enrolled-courses.service';

@Component({
  selector: 'app-course-enrollment',
  templateUrl: './course-enrollment.component.html',
  styleUrls: ['./course-enrollment.component.css']
})
export class CourseEnrollmentComponent implements OnInit {
  courses: Course[] = [];
  enrollments: CourseEnrollment[] = [];

  constructor(
    private courseService: CourseService,
    private enrollmentService: CourseEnrollmentsService
  ) {}

  ngOnInit(): void {
    this.courseService.getCourses().subscribe(courses => {
      this.courses = courses;
    });

    this.enrollmentService.getEnrollmentsByStudentId(1).subscribe(enrollments => {
      this.enrollments = enrollments;
    });
  }

  enroll(courseId: number): void {
    const enrollment: CourseEnrollment = {
      studentId: ''
      courseId
    };

    this.enrollmentService.enroll(enrollment).subscribe(enrollment => {
      this.enrollments.push(enrollment);
    });
  }

  unenroll(enrollmentId: number): void {
    this.enrollmentService.deleteEnrollment(enrollmentId).subscribe(() => {
      this.enrollments = this.enrollments.filter(e => e.id !== enrollmentId);
    });
  }

}
