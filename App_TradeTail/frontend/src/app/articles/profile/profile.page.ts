import { Component, Input, OnInit } from '@angular/core';
import { CommonModule, NgClass, TitleCasePipe } from '@angular/common';
import { FormsModule, NgModel, NgModelGroup } from '@angular/forms';
import { IonicModule } from '@ionic/angular';

import { User } from 'src/app/core/models/User';
import { StorageService } from 'src/app/core/services/storageService';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.page.html',
  styleUrls: ['./profile.page.scss'],
  standalone: true,
  imports: [IonicModule, TitleCasePipe, NgClass],
})
export class ProfilePage implements OnInit {
  constructor(private storageService: StorageService, private router: Router) {}

  @Input() user!: User;

  logout() {
    this.storageService.clear();
    console.log(this.storageService.recall('token'));
    console.log(this.storageService.recall('user'));
    this.router.navigate(['auth/login']);
  }

  ngOnInit() {}
}
