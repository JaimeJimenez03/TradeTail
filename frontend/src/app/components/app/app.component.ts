import { Component } from '@angular/core';
import { HomeComponent } from '../home/home.component';
import { CommonModule } from '@angular/common';
import { FooterComponent } from '../../templates/footer/footer.component';
import { OrdersComponent } from '../orders/orders.component';
import { ProfileComponent } from '../profile/profile.component';
import { User } from '../../models/user';

@Component({
  selector: 'app-app',
  standalone: true,
  imports: [
    FooterComponent,
    HomeComponent,
    OrdersComponent,
    ProfileComponent,
    CommonModule,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  user!: User;
  home: boolean = false;
  orders: boolean = false;
  profile: boolean = true;

  ngOnInit(): void {
    if (history.state) {
      this.user = history.state.user;
    }
  }

  view(selecction: string) {
    if (selecction === 'home') {
      this.home = true;
      this.orders = false;
      this.profile = false;
    } else if (selecction === 'orders') {
      this.home = false;
      this.orders = true;
      this.profile = false;
    } else if (selecction === 'profile') {
      this.home = false;
      this.orders = false;
      this.profile = true;
    }
  }
}
