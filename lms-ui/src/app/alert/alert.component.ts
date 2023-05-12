import {Component, OnInit} from '@angular/core';
import {Alert} from './alert.model';
import {AlertService} from './alert.service';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css']
})
export class AlertComponent implements OnInit {

  alerts: Alert[] = [];

  constructor(private alertService: AlertService) {
  }

  ngOnInit(): void {
    this.alertService.alertSubject.subscribe(alert => this.alerts.push(alert));
    this.alertService.alertClearSubject.subscribe(() => this.alerts = []);
  }

  close(alert: Alert): void {
    const index = this.alerts.indexOf(alert);
    if (index >= 0) {
      this.alerts.splice(index, 1);
    }
  }
}
