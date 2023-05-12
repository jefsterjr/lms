import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CourseEnrollmentsRoutingModule } from './enrolled-courses-routing.module';
import { CourseEnrollmentComponent } from './course-enrollment/course-enrollment.component';


@NgModule({
  declarations: [
    CourseEnrollmentComponent
  ],
  imports: [
    CommonModule,
    CourseEnrollmentsRoutingModule
  ]
})
export class CourseEnrollmentsModule { }
