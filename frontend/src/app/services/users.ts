import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class UsersService {
  constructor(private http: HttpClient) { }
  public getUsers(): Observable<any> {
    return this.http.get<string>('api/users');
  }
}
