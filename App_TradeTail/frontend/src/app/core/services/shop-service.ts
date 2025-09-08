import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { Category, Shop } from '../models/Shop';

@Injectable({
  providedIn: 'root',
})
export class ShopService {
  private API = environment.API_URL;

  constructor(private http: HttpClient) {}

  getCategories() {
    let url = `${this.API}/api/shops/categories`;
    return this.http.get<Category[]>(url);
  }

  getCategory(id: number) {
    let url = `${this.API}/api/shops/category`;
    return this.http.post<Category>(url, id);
  }

  getShops() {
    let url = `${this.API}/api/shops`;
    return this.http.get<Shop[]>(url);
  }

  addShop(shop: Shop) {
    let url = `${this.API}/api/shops/add`;
    return this.http.post<Shop[]>(url, shop);
  }
}
