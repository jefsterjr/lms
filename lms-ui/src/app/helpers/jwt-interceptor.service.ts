import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {AuthService} from '../auth/auth.service';

@Injectable()
export class JwtInterceptorService implements HttpInterceptor {

  constructor(private authenticationService: AuthService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = sessionStorage.getItem('token');
    if (token) {
      // @ts-ignore
      req = req.clone({
        headers: req.headers.set('Authorization', token),
        url: environment.baseURL + req.url
      });
    }

    const user = this.authenticationService.userValue;
    const isLoggedIn = user && user.token;
    if (isLoggedIn) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${user.token}`
        }
      });
    }
    return next.handle(req);
  }
}
