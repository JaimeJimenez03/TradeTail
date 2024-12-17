import { Component } from '@angular/core';
import { slideInOutAnimation } from '../../animations';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css',
  animations: [slideInOutAnimation],
})
export class OrdersComponent {}
