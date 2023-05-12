import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TaskLogListComponent } from './task-logs-list/task-log-list.component';
import { TaskLogFormComponent } from './task-logs-form/task-log-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';



@NgModule({
  declarations: [
    TaskLogListComponent,
    TaskLogFormComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ]
})
export class TaskLogsModule { }
