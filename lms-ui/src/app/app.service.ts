import {Injectable} from '@angular/core';
import {AlertService} from './alert/alert.service';
import {HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(protected alertService: AlertService) {
  }

  protected handleError(error: HttpErrorResponse): Observable<any> {
    if (error.status === 400 || error.status === 404) {
      error.error.messages.forEach((message: string) => this.alertService.warning(message));
    } else {
      this.alertService.error(error.message);
    }
    return throwError(error);
  }
}
