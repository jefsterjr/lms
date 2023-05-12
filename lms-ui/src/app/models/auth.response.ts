export class AuthResponse {
  constructor(
    public token?: string,
    public id?: number,
    public username?: string,
    public email?: string,
    public role?: string,
  ) {
  }
}
