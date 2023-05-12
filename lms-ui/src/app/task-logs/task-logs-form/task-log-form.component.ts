import {Component, OnInit} from '@angular/core';
import {TaskLogsService} from '../task-logs-service';
import {TaskLog} from '../../models/task-log';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-task-logs-form',
  templateUrl: './task-log-form.component.html'
})
export class TaskLogFormComponent implements OnInit {

  taskLogs: TaskLog[] | undefined;

  taskLogForm: FormGroup = this.formBuilder.group({
    date: ['', Validators.required],
    category: ['', Validators.required],
    description: ['', Validators.required],
    timeSpent: ['', Validators.required]
  });

  constructor(private taskLogService: TaskLogsService, private formBuilder: FormBuilder,) {
  }

  ngOnInit(): void {
    this.getTaskLogs();
  }

  getTaskLogs(): void {
    this.taskLogService.getTaskLogs().subscribe((taskLogs) => {
      this.taskLogs = taskLogs;
    });
  }

  addTaskLog(taskLogFormValue: FormGroup): void {
    // @ts-ignore
    const {date, category, description, timeSpent} = taskLogFormValue;
    const taskLog: TaskLog = {id: 0, date, category, description, timeSpent};
    this.taskLogService.addTaskLog(taskLog).subscribe(() => {
      this.getTaskLogs();
      taskLogFormValue.reset();
    });
  }

  updateTaskLog(taskLog: TaskLog): void {
    this.taskLogService.updateTaskLog(taskLog).subscribe(() => {
      this.getTaskLogs();
    });
  }

  deleteTaskLog(id: number): void {
    this.taskLogService.deleteTaskLog(id).subscribe(() => {
      this.getTaskLogs();
    });
  }
}
