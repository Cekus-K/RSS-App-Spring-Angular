import { Component, OnInit } from '@angular/core';
import {User} from '../../common/user';
import {RegisterService} from '../../services/register.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  username: string;
  password: string;
  passwordConfirm: string;
  email: string;
  registerSuccess = false;
  successMessage: string;

  constructor(private registerService: RegisterService) { }

  ngOnInit(): void {
  }

  handleRegister() {
    this.registerService.addUser(new User(this.username, this.password, this.passwordConfirm, this.email)).subscribe(data => {
      this.registerSuccess = true;
      this.successMessage = 'Register Successful: ' + data;
    });
  }
}
