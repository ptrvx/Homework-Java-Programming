import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { User } from './user';
import { LoginService } from '../services/login.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  public user: User;
  errorMessage: string;

  constructor(private router: Router, private loginService: LoginService) {
    this.user = new User('','');
  }

  ngOnInit() {
  }

  public authenticate(user: User) {
    this.loginService.authenticate(user).subscribe(
      usr => { this.user = usr; console.log(usr); this.router.navigateByUrl('/'); },
      error => { this.errorMessage = error; }
    );
  }

}
