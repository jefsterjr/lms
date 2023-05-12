import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AlertService} from '../alert/alert.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AuthService} from './auth.service';
import {SignUpForm} from './forms/signup.form';
import {SignInForm} from './forms/signin.form';
import {CustomValidationServiceService} from '../helpers/custom-validation-service.service';
import {AuthResponse} from '../models/auth.response';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html'
})
export class AuthComponent implements OnInit {

  viewMode = 'login';
  submitted = false;


  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authService: AuthService,
    private alertService: AlertService,
    private customValidator: CustomValidationServiceService
  ) {
  }

  signInformGroup: FormGroup = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });

  signUpFormGroup: FormGroup = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      email: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      address: ['', Validators.required],
      password: ['', Validators.compose([Validators.required, this.customValidator.patternValidator()])],
      confirmPassword: ['', Validators.required]
    },
    {
      validator: this.customValidator.matchPassword('password', 'confirmPassword'),
    }
  );

  ngOnInit(): void {
    sessionStorage.setItem('token', '');
  }

  onSubmit(): void {
    this.submitted = true;
    console.log(this.signInformGroup);
    if (this.signInformGroup.valid) {
      const signInForm: SignInForm = this.signInformGroup.value;
      const response: AuthResponse = this.authService.signIn(signInForm);
      if (response) {
        this.router.navigate(['/']);
        this.signInformGroup.get('username')?.setValue(response.username);
      }

    }
  }


  onSignUp(): void {
    this.submitted = true;
    console.log(this.signUpFormGroup.valid);
    if (this.signUpFormGroup.valid) {
      this.alertService.clearAlerts();
      const signUp: SignUpForm = this.signUpFormGroup.value;
      this.authService.signUp(signUp).subscribe(() => {
        this.alertService.success('User created, enter your credentials');
        this.viewMode = 'login';
      });
    }
  }

  isFieldInvalid(field: string, form: FormGroup) {
    if (form) {
      // @ts-ignore
      return ((!form.get(field).valid && form.get(field).touched) || (form.get(field).untouched && this.submitted));
    }
    return true;
  }

  cancel(): void {
    this.signUpFormGroup.reset();
    this.viewMode = 'login';
  }
}
