import { Component, OnInit } from '@angular/core';
import { Token } from './model/token';
import { Router } from '@angular/router';
import { AuthenticationService } from './service/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  token: Token

  constructor(
    private router: Router,
    private auth: AuthenticationService
  ) { }

  ngOnInit() {
    this.auth.tokenSubject.subscribe((t) => {
      console.log('token received')
      console.dir(t)
      this.token = t
    })
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }
}
