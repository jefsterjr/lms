import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {AuthService} from '../auth/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn$: Observable<boolean> | undefined;

  constructor(private authService: AuthService) {
  }

  // tslint:disable-next-line:typedef
  ngOnInit() {
    this.isLoggedIn$ = this.authService.isLoggedIn;
  }

  onLogout(): void {
    this.authService.logout();
  }

}
