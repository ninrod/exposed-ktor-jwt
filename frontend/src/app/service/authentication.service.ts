import { Injectable } from '@angular/core'
import { HttpClient } from '@angular/common/http'
import { BehaviorSubject, Observable } from 'rxjs'
import { tap } from 'rxjs/operators'
import { Token } from '../model/token'

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  public tokenSubject: BehaviorSubject<Token> = new BehaviorSubject<Token>(null)

  constructor(private http: HttpClient) {
    let token = localStorage.getItem('token')
    if (token) {
      this.tokenSubject.next(JSON.parse(token))
    }
  }

  login(username: string, password: string): Observable<Token> {
    return this.http.post<Token>('/api/login', {
      name: username,
      password: password
    }).pipe(tap(token => {
      if (token) {
        localStorage.setItem('token', JSON.stringify(token))
        this.tokenSubject.next(token)
      }
    }))
  }

  logout() {
    localStorage.removeItem('token')
    this.tokenSubject.next(null)
  }
}
