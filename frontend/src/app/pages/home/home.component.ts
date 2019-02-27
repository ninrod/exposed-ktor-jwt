import { Component, OnInit } from '@angular/core'
import { User } from 'src/app/model/user'
import { UsersService } from 'src/app/service/user.service'

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  users: User[]

  constructor(private us: UsersService) { }

  ngOnInit() {
    this.us.getUsers().subscribe(users => this.users = users)
  }
}
