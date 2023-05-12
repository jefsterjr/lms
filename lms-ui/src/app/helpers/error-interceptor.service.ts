import {Injectable} from '@angular/core';
import {AuthService} from '../auth/auth.service';
import {HttpEvent, HttpHandler, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import {catchError} from 'rxjs/operators';

@Injectable()
export class ErrorInterceptorService {
  constructor(private authenticationService: AuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(request).pipe(catchError(err => {
      if ([401, 403].indexOf(err.status) !== -1) {
        // auto logout if 401 Unauthorized or 403 Forbidden response returned from api
        this.authenticationService.logout();
      }

      const error = 'Authentication error';
      return throwError(error);
    }));
  }
}
