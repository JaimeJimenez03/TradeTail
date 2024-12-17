import { AuthService } from './../../services/auth.service';
import { Component } from '@angular/core';
import { User } from '../../models/user';
import { Router } from '@angular/router';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { FooterComponent } from '../../templates/footer/footer.component';
import { slideInOutAnimation } from '../../animations';
import { CommonModule, NgIf, NgSwitch } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FooterComponent, CommonModule, NgSwitch],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  animations: [slideInOutAnimation],
})
export class HomeComponent {
  user!: User;
  avatarUrl!: SafeUrl;

  currentSection: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    if (history.state) {
      this.user = history.state.user;
    }
  }

  getAvatarUrl(): string | undefined {
    if (this.user && this.user.avatar) {
      // Construye la URL completa de la imagen de avatar
      return `http://localhost:8080/src/main/resources/media/images/avatar/${this.user.avatar}`;
    }
    return undefined;
  }

  logout() {
    this.authService.logout(this.user).subscribe((resp) => {});
  }
}
