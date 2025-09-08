import { MunicipalitiesGroup } from './../../core/services/municipalities-group';
import { IonicModule } from '@ionic/angular';
import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { User } from 'src/app/core/models/User';
import { Ccaa, Municipality, Province } from 'src/app/core/models/Municipality';
import { AuthService } from 'src/app/core/services/auth-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule],
})
export class RegisterPage implements OnInit {
  constructor(
    private municipalitiesGroup: MunicipalitiesGroup,
    private authService: AuthService,
    private router: Router
  ) {}

  provinces: Province[] = [];
  municipalities: Municipality[] = [];

  userRegister: User = {
    id: 0,
    avatar: '',
    email: '',
    first_name: '',
    is_active: false,
    last_name: '',
    name: '',
    password: '',
    role: 'USER',
    telephone_number: '',
    token: '',
    username: '',
    municipality_id: null,
    active: false,
  };

  showPassword = false;
  passwordMismatch = false;

  selectedProvince: number | null = null;
  filteredProvinces: any[] = [];
  filteredMunicipalities: any[] = [];

  onSubmit() {
    console.log('Registrando usuario:', this.userRegister);

    this.authService.register(this.userRegister).subscribe({
      next: (resp) => {
        console.log(resp);
        this.router.navigate(['auth/login']);
      },
    });
  }

  goToLogin() {}

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  onProvinceChange(event: any): void {
    const provinceId = +event.detail.value;

    this.filteredMunicipalities = this.municipalities.filter(
      (m) => m.province.id === provinceId
    );
  }

  ngOnInit(): void {
    this.municipalitiesGroup.getAllProvinces().subscribe({
      next: (resp) => {
        this.provinces = resp;
      },
    });

    this.municipalitiesGroup.getAllMunicipalities().subscribe({
      next: (resp) => {
        this.municipalities = resp;
      },
    });
  }
}
