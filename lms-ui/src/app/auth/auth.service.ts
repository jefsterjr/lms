import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {AlertService} from '../alert/alert.service';
import {AppService} from '../app.service';
import {SignUpForm} from './forms/signup.form';
import {AuthResponse} from '../models/auth.response';
import {Router} from '@angular/router';
import {User} from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService extends AppService {

  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  authApiUrl = '/api/auth';

  constructor(private http: HttpClient, protected alertService: AlertService, private router: Router) {
    super(alertService);
    // @ts-ignore
    this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  get isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  signIn(signUpForm: SignUpForm): any {
    console.log(signUpForm);
    // @ts-ignore
    this.http.post<AuthResponse>(`${this.authApiUrl}/signin`, signUpForm)
      .pipe(
        catchError(err => this.handleError(err))
      ).subscribe(response => {
      if (response) {
        localStorage.setItem('user', JSON.stringify(response));
        // @ts-ignore
        this.userSubject.next(response);
        this.loggedIn.next(true);
      }
      return response;
    });
  }


  signUp(signUpForm: SignUpForm): Observable<any> {
    return this.http.post(this.authApiUrl + '/signup', signUpForm)
      .pipe(
        catchError(err => this.handleError(err))
      );
  }


  logout(): void {
    // remove user from local storage to log user out
    localStorage.removeItem('user');
    // @ts-ignore
    this.userSubject.next(null);
    this.router.navigate(['/auth']);
  }
}
