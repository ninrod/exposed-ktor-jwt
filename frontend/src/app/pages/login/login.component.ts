import { Component, OnInit } from '@angular/core'
import { Router, ActivatedRoute } from '@angular/router'
import { NgForm } from '@angular/forms'
import { first } from 'rxjs/operators'
import { AuthenticationService } from '../../service/authentication.service'


@Component({
  selector: 'app-login',
  templateUrl: 'login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loading = false
  submitted = false
  returnUrl: string
  error = ''

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
  }

  onSubmit(form: NgForm) {
    console.dir(form)
    console.dir(form.value)

    this.submitted = true

    // stop here if form is invalid
    if (form.invalid) {
      return
    }

    this.loading = true

    this.authenticationService.login(form.value["username"], form.value["password"])
      .pipe(first())
      .subscribe(() => this.router.navigate([this.returnUrl]),
        () => {
          this.error = "invalid user/password"
          this.loading = false
        })
  }
}
