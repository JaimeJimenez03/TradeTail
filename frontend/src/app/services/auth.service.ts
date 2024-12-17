import {
  HttpClient,
  HttpClientModule,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { environment } from '../environments/environment.development';
import { Router } from '@angular/router';
import { AuthUser } from '../models/auth-user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl: String = environment.API_URL;

  constructor(private http: HttpClient, private router: Router) {}

  login(username: String, password: String) {
    const user: AuthUser = {
      username: username,
      password: password,
    };
    const url = `${this.baseUrl}/login`;
    return this.http.post<any>(url, user);
  }

  register(user: User, avatar?: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append(
      'user',
      new Blob([JSON.stringify(user)], { type: 'application/json' })
    );
    if (avatar) {
      formData.append('avatar', avatar);
    }

    const url = `${this.baseUrl}/register`;
    return this.http.post<any>(url, formData, {
      headers: new HttpHeaders({
        Accept: 'application/json',
      }),
    });
  }

  getToken(token: String) {
    const url = `${this.baseUrl}/getToken`;
    return this.http.post<User>(url, token);
  }

  logout(user: User) {
    const url = `${this.baseUrl}/removeToken`;
    localStorage.removeItem('token');
    this.router.navigate(['/login']);

    return this.http.post<User>(url, user);
  }

  getAvatar(filename: String) {
    const url = `${this.baseUrl}/resources/media/images/avatar/${filename}`;
    return this.http.get<String>(url);
  }
}
