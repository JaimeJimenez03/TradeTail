import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [],
  templateUrl: './welcome.component.html',
  styleUrl: './welcome.component.css',
})
export class WelcomeComponent {
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit() {
    setTimeout(() => {
      if (localStorage.getItem('token')) {
        this.authService.getToken(localStorage.getItem('token')!).subscribe(
          (resp) => {
            this.router.navigate(['/app'], { state: { user: resp } });
          },
          (error) => {
            console.error('Error en el sistema ', error);
          }
        );
      } else {
        this.router.navigate(['/login']);
      }
    }, 3000);
  }
}
