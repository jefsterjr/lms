import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AlertComponent} from './alert/alert.component';
import {CourseFormComponent} from './course/course-form/course-form.component';
import {CourseListComponent} from './course/course-list/course-list.component';
import {AuthComponent} from './auth/auth.component';
import {NavbarComponent} from './navbar/navbar.component';
import {JwtInterceptorService} from './helpers/jwt-interceptor.service';
import {ErrorInterceptorService} from './helpers/error-interceptor.service';

@NgModule({
  declarations: [
    AppComponent,
    AlertComponent,
    CourseFormComponent,
    CourseListComponent,
    AuthComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptorService, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
