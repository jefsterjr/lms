import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CourseFormComponent} from './course/course-form/course-form.component';
import {CourseListComponent} from './course/course-list/course-list.component';
import {AuthComponent} from './auth/auth.component';
import {TaskLogFormComponent} from './task-logs/task-logs-form/task-log-form.component';

const routes: Routes = [
  {path: '', redirectTo: '/auth', pathMatch: 'full'},
  {path: 'auth', component: AuthComponent},
  {
    path: 'courses', component: CourseListComponent,
    children: [
      {path: 'create', component: CourseFormComponent},
      {path: 'edit/:id', component: CourseFormComponent}
    ],
  }, {
    path: 'enrolled-courses', component: CourseListComponent,
  },
  {
    path: 'task-logs', component: TaskLogFormComponent, // <-- add this route for the TaskLogComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
