export class User {
  username: string;
  password: string;
  passwordConfirm: string;
  email: string;

  constructor(username: string, password: string, passwordConfirm: string, email: string) {
    this.username = username;
    this.password = password;
    this.passwordConfirm = passwordConfirm;
    this.email = email;
  }
}
