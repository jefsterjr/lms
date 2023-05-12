import {Component, OnInit} from '@angular/core';
import {CourseService} from '../course.service';
import {Course} from '../../models/course';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html'
})
export class CourseListComponent implements OnInit {

  courses: Course[] = [];

  constructor(
    private courseService: CourseService,
    private route: ActivatedRoute,
    private router: Router,
  ) {
  }

  ngOnInit(): void {
    this.courseService.listCourses().subscribe(courses => {
      this.courses = courses;
      console.log(this.courses);
    });
  }

  create(): void {
    this.router.navigate(['create'], {relativeTo: this.route});
  }

  edit(course: Course): void {
    this.router.navigate([`edit/${course.id}`], {relativeTo: this.route});
  }

  delete(course: Course): void {
    this.courseService.deleteCourse(course.id).subscribe(() => this.ngOnInit());
  }
}
