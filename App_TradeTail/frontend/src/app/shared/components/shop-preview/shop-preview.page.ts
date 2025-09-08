import { IonicModule } from '@ionic/angular';
import { Component, Input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Shop } from 'src/app/core/models/Shop';

@Component({
  selector: 'app-shop-preview',
  templateUrl: './shop-preview.page.html',
  styleUrls: ['./shop-preview.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule],
})
export class ShopPreviewPage implements OnInit {
  @Input() shop!: Shop;

  constructor() {}

  ngOnInit() {}
}
