import {Component, OnInit} from '@angular/core';
import {TaskLogsService} from '../task-logs-service';
import {Router} from '@angular/router';
import {TaskLog} from '../../models/task-log';

@Component({
  selector: 'app-task-logs-list',
  templateUrl: './task-log-list.component.html'
})
export class TaskLogListComponent implements OnInit {

  taskLogs: TaskLog[] = [];

  constructor(private taskLogService: TaskLogsService, private router: Router) {}

  ngOnInit(): void {
    this.getTaskLogs();
  }

  getTaskLogs(): void {
    this.taskLogService.getTaskLogs()
      .subscribe(taskLogs => this.taskLogs = taskLogs);
  }

  editTaskLog(id: number): void {
    this.router.navigate([`edit/${id}`]);
  }

  create(): void {
    this.router.navigate(['task-log']);
  }

}
