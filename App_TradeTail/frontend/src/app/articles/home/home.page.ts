import { ShopPreviewPage } from './../../shared/components/shop-preview/shop-preview.page';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { User } from 'src/app/core/models/User';
import { ShopService } from 'src/app/core/services/shop-service';
import { StorageService } from 'src/app/core/services/storageService';
import { ProfilePage } from '../profile/profile.page';
import { Category, Shop } from 'src/app/core/models/Shop';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgClass } from '@angular/common';
import { IonSearchbar } from '@ionic/angular';

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
  standalone: true,
  imports: [
    IonicModule,
    ProfilePage,
    FormsModule,
    NgClass,
    ShopPreviewPage,
    CommonModule,
  ],
})
export class HomePage implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private storageService: StorageService,
    private shopService: ShopService,
    private router: Router
  ) {}

  user: User = {
    id: 0,
    avatar: '',
    email: '',
    first_name: '',
    is_active: false,
    last_name: '',
    name: '',
    password: '',
    role: '',
    telephone_number: '',
    token: '',
    username: '',
    municipality_id: null,
    active: true,
  };

  shop: Shop = {
    id: 0,
    image: '',
    name: '',
    description: '',
    t_contact: [] as string[],
    address: '',
    latitude: 0,
    longitude: 0,
    stars: 0,
    category: null,
    users: [],
    municipality: null,
  };

  @ViewChild('pageHeader', { static: false }) pageHeader!: ElementRef;
  @ViewChild('shopScroll', { static: false }) shopScroll!: ElementRef;

  shops: Shop[] = [];
  shopsByCategory: any[] = [];

  categories: Category[] = [];
  category!: Category;

  newPhone = '';

  showPhoneModal = false;

  showAllShops: boolean = true;

  onSubmit(form: any): void {
    if (form.valid) {
      console.log('Tienda registrada:', this.shop);
      this.shop.users.push(this.user);

      this.shopService.addShop(this.shop).subscribe({
        next: (resp) => {
          this.shops = resp;
        },
      });
    }
  }

  getShops() {
    this.showAllShops = true;
    this.shopService.getShops().subscribe({
      next: (resp) => {
        this.shops = resp;
        this.results = [...resp];
      },
      error: (err) => {},
    });
  }

  getCateories() {
    this.shopService.getCategories().subscribe({
      next: (resp) => {
        this.categories = resp;
      },
      error: (err) => {},
    });
  }

  listCategories(id: number) {
    this.showAllShops = false;
    this.shopsByCategory = this.shops.filter(
      (shop: any) => shop.category.id === id
    );

    this.shopService.getCategory(id).subscribe({
      next: (resp) => {
        this.category = resp;
      },
    });
  }

  selectedPhones: string[] = [];
  invalid: boolean = false;

  addPhone() {
    const phone = this.newPhone.trim();

    if (!this.shop.t_contact) {
      this.shop.t_contact = [];
    }

    const isValid = /^([67]\d{8})$/.test(phone);

    if (isValid && !this.shop.t_contact.includes(phone)) {
      this.shop.t_contact.push(phone);
      this.closePhoneModal();
      this.invalid = false;
    } else {
      this.invalid = true;
    }
  }

  openPhoneModal() {
    this.showPhoneModal = true;
  }

  closePhoneModal() {
    this.showPhoneModal = false;
    this.newPhone = '';
  }

  /*getClass(control: any): string {
    return control?.invalid && control?.touched
      ? 'invalid'
      : control?.valid && control?.touched
      ? 'valid'
      : '';
  }*/

  getClass(control: any): string {
    return '';
  }

  return() {
    this.router.navigate(['/home']);
  }

  public results: Shop[] = [];

  scrollToTop() {
    if (this.pageHeader) {
      this.pageHeader.nativeElement.scrollIntoView({
        behavior: 'smooth',
        block: 'start',
      });
    }

    if (this.shopScroll) {
      this.shopScroll.nativeElement.scrollTop = 0;
    }
  }

  @ViewChild(IonSearchbar) searchbar!: IonSearchbar;

  showSearchBar() {
    this.showAllShops = true;

    setTimeout(() => {
      if (this.searchbar) {
        this.searchbar.setFocus();
      }
    }, 100); // 100ms asegura que Angular haya renderizado completamente
  }

  async handleInput(event: Event) {
    const target = event.target as HTMLIonSearchbarElement;
    const query = target.value?.toLowerCase() || '';

    if (this.shops) {
      this.results = this.shops.filter((d: any) =>
        d.name.toLowerCase().includes(query)
      );
    } else {
      this.results = [];
    }
  }

  async ngOnInit() {
    this.user = await this.storageService.recall('user', '');

    this.getShops();
    this.getCateories();
  }
}
