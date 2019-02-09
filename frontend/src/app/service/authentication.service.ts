import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { Token } from '../model/token';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  public tokenSubject: BehaviorSubject<Token>;

  constructor(private http: HttpClient) {
    this.tokenSubject = new BehaviorSubject<Token>(JSON.parse(localStorage.getItem('token')));
  }

  login(username: string, password: string) {
    return this.http.post<any>('/api/login', {
      "name": username,
      "password": password
    }).pipe(map(token => {
      if (token) {
        localStorage.setItem('token', JSON.stringify(token));
        this.tokenSubject.next(token);
      }
      return token;
    }));
  }

  logout() {
    localStorage.removeItem('token');
    this.tokenSubject.next(null);
  }
}
