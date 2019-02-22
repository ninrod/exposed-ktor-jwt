import { Component, OnInit, ViewChild } from '@angular/core'
import { Router, ActivatedRoute } from '@angular/router'
import { FormGroup, FormControl, Validators } from '@angular/forms'
import { first } from 'rxjs/operators'
import { AuthenticationService } from '../../service/authentication.service'


@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  returnUrl: string
  error = ''

  loginForm: FormGroup

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    // reset login status
    this.authenticationService.logout()

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/'

    this.loginForm = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, [Validators.required])
    })
  }

  onSubmit() {
    console.dir(this.loginForm)
    console.dir(this.loginForm.value)

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return
    }


    this.authenticationService.login(this.loginForm.value["username"], this.loginForm.value["password"])
      .pipe(first())
      .subscribe(() => this.router.navigate([this.returnUrl]),
        () => {
          this.error = "invalid user/password"
        })
  }
}
