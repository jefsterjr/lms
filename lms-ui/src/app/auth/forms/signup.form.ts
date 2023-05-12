export class SignUpForm {
  constructor(
    public firstName?: string,
    public lastName?: string,
    public dateOfBirth?: Date,
    public email?: string,
    public phoneNumber?: string,
    public address?: string,
    public password?: string
  ) {
  }
}
