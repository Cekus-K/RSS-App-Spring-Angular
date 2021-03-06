import { Injectable } from '@angular/core';
import {User} from '../common/user';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  baseUrl = 'http://localhost:8080/api/user/';

  constructor(private http: HttpClient) { }

  addUser(user: User): Observable<string> {
    return this.http.post<string>(this.baseUrl + 'register', user);
  }
}
