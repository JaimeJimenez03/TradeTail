import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { Auth, User } from '../models/User';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private API = environment.API_URL;

  constructor(private http: HttpClient) {}

  login(auth: Auth) {
    let url = `${this.API}/api/auth/login`;

    return this.http.post<any>(url, auth);
  }

  register(user: User) {
    let url = `${this.API}/api/auth/register`;

    return this.http.post<User>(url, user);
  }
}
