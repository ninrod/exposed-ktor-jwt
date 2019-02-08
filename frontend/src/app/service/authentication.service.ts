import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../model/user';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private tokenSubject: BehaviorSubject<User>;

  constructor(private http: HttpClient) {
    this.tokenSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('token')));
  }

  public get tokenValue(): User {
    return this.tokenSubject.value;
  }

  login(username: string, password: string) {
    return this.http.post<any>('/api/login', {
      "name": username,
      "password": password
    }).pipe(map(token => {
      if (token) {
        localStorage.setItem('token', token);
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
