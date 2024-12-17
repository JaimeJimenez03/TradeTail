import { routes } from './../../app.routes';
import { DomSanitizer } from '@angular/platform-browser';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { fadeAnimation, slideInOutAnimation } from '../../animations';
import { User } from '../../models/user';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [RouterLink, EditProfileComponent],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
  animations: [slideInOutAnimation, fadeAnimation],
})
export class ProfileComponent {
  @Input() user!: User;
  @Output() userAct: EventEmitter<User> = new EventEmitter();

  editProfile: boolean = true;

  constructor(
    private authService: AuthService,
    private routesouter: Router,
    private domSanitizer: DomSanitizer
  ) {}

  ngOnInit() {
    console.log(this.user);
    this.editProfile = false;
  }

  logout() {
    this.authService.logout(this.user).subscribe((resp) => {});
  }
}
