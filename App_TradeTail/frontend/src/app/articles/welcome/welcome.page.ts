import { StorageService } from './../../core/services/storageService';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import {
  IonContent,
  IonHeader,
  IonTitle,
  IonToolbar,
} from '@ionic/angular/standalone';
import { Router } from '@angular/router';
import { IonicModule } from '@ionic/angular';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.page.html',
  styleUrls: ['./welcome.page.scss'],
  standalone: true,
  imports: [CommonModule, FormsModule, IonicModule],
})
export class WelcomePage implements OnInit {
  constructor(private router: Router, private storageService: StorageService) {}

  async ngOnInit() {
    const token = await this.storageService.recall('token');

    if (token && token !== 'null') {
      setTimeout(() => {
        this.router.navigate(['/home']);
      }, 5000);
    } else {
      setTimeout(() => {
        this.router.navigate(['/auth/login']);
      }, 5000);
    }
  }
}
