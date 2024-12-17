import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { User } from '../../models/user';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, MatFormFieldModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  user: User = {
    username: '',
    password: '',
    name: '',
    first_name: '',
    last_name: '',
    email: '',
    telephone_number: '',
    city: '',
    avatar: '',
    municipality: '',
  };

  selectedFile!: File;
  imagePreview!: string | ArrayBuffer | null;

  constructor(private authService: AuthService, private router: Router) {}

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    const reader = new FileReader();
    reader.onload = () => {
      this.imagePreview = reader.result;
    };
    reader.readAsDataURL(this.selectedFile!);
  }

  onSubmit() {
    console.log(this.user);
    this.user.avatar = this.selectedFile.name;
    this.authService.register(this.user, this.selectedFile).subscribe(
      (resp) => {
        this.router.navigate(['/login']);
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
