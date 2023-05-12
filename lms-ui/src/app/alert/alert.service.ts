import {Injectable} from '@angular/core';
import {Alert} from './alert.model';
import {Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlertService {

  alertSubject: Subject<Alert> = new Subject<Alert>();
  alertClearSubject: Subject<void> = new Subject<void>();

  constructor() {
  }

  public warning(message: string): void {
    this.alertSubject.next(new Alert('warning', message));
  }

  public error(message: string): void {
    this.alertSubject.next(new Alert('danger', message));
  }

  public clearAlerts(): void {
    this.alertClearSubject.next();
  }

  success(message: string): void {
    this.alertSubject.next(new Alert('success', message));
  }
}
