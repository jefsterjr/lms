import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {TaskLog} from '../models/task-log';

@Injectable({
  providedIn: 'root'
})
export class TaskLogsService {

  apiUrl = '/api/task-logs';
  constructor(private http: HttpClient) {}

  getTaskLogs(): Observable<TaskLog[]> {
    return this.http.get<TaskLog[]>(this.apiUrl);
  }

  addTaskLog(taskLog: TaskLog): Observable<TaskLog> {
    return this.http.post<TaskLog>(this.apiUrl, taskLog);
  }

  updateTaskLog(taskLog: TaskLog): Observable<void> {
    const url = `${this.apiUrl}/${taskLog.id}`;
    return this.http.put<void>(url, taskLog);
  }

  deleteTaskLog(id: number): Observable<void> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
