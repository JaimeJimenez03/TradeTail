import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  username: String = '';
  password: String = '';

  constructor(private authService: AuthService, private router: Router) {}

  NgOnInit() {
    if (localStorage.getItem('token') === null) {
      console.log('token nulo');
    } else {
      localStorage.getItem('token');
    }
  }

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe(
      (resp) => {
        localStorage.setItem('token', resp.token);
        this.router.navigate(['/app'], { state: { user: resp.user } });
      },
      (error) => {
        console.log('Error de sistema', error);
      }
    );
  }
}
