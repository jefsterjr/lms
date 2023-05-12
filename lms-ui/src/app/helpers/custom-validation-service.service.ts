import {Injectable} from '@angular/core';
import {AbstractControl, FormGroup, ValidatorFn} from '@angular/forms';
import {AlertService} from '../alert/alert.service';

@Injectable({
  providedIn: 'root'
})
export class CustomValidationServiceService {

  constructor(private alertService: AlertService) {
  }

  patternValidator(): ValidatorFn {
    return (control: AbstractControl): { [p: string]: any } | null => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$');
      const valid = regex.test(control.value);
      return valid ? null : {invalidPassword: true};
    };
  }

  matchPassword(password: string, confirmPassword: string) {
    // @ts-ignore
    return (formGroup: FormGroup) => {
      console.log(formGroup);
      const passwordControl = formGroup.controls[password];
      const confirmPasswordControl = formGroup.controls[confirmPassword];

      if (!passwordControl || !confirmPasswordControl) {
        return null;
      }

      if (confirmPasswordControl.errors && !confirmPasswordControl.errors.passwordMismatch) {
        return null;
      }

      if (passwordControl.value !== confirmPasswordControl.value) {
        confirmPasswordControl.setErrors({passwordMismatch: true});
      } else {
        confirmPasswordControl.setErrors(null);
      }
    };
  };

}
