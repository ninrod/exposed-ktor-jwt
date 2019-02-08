import { Component, OnInit } from '@angular/core';
import { UsersService } from './services/users';
import { User } from './model/user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  users: User[]

  constructor(private us: UsersService) { }
  ngOnInit() {
    this.us.getUsers().subscribe(
      (users) => this.users = users
    )
  }
}
