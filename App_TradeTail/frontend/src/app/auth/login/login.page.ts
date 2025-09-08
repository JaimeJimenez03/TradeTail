import { AuthService } from './../../core/services/auth-service';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/core/services/storageService';
import { Auth } from 'src/app/core/models/User';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, IonicModule],
})
export class LoginPage implements OnInit {
  auth: Auth = {
    email: '',
    password: '',
  };

  showPassword: boolean = false;
  passwordTouched: boolean = false;

  databaseError: boolean = false;
  pswI: boolean = false;
  otherError: boolean = false;

  constructor(
    private authService: AuthService,
    private router: Router,
    private storageService: StorageService
  ) {}

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  login() {
    this.passwordTouched = true;
    if (!this.auth.email || !this.auth.password) {
      return;
    }

    this.authService.login(this.auth).subscribe({
      next: (resp) => {
        this.storageService.store('user', resp.user);
        this.storageService.store('token', resp.token);
        this.router.navigate(['home']);
      },
      error: (err) => {
        if (err.status === 400) {
          // Error de email no registrado o email en uso
          this.pswI = true;
          this.databaseError = false;
          this.otherError = false;
        } else if (err.status === 401) {
          // Contraseña incorrecta
          this.pswI = true;
          this.databaseError = false;
          this.otherError = false;
        } else if (err.status === 500) {
          // Problema con la base de datos o error inesperado
          this.databaseError = true;
          this.pswI = true;
          this.otherError = false;
        } else {
          // Otros errores
          this.otherError = true;
          this.databaseError = true;
          this.pswI = true;
        }
      },
    });
  }

  register() {
    this.router.navigate(['/auth/register']);
  }

  ngOnInit() {}
}
