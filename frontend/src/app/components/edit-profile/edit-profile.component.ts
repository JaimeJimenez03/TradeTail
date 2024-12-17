import { Component, EventEmitter, Input, Output } from '@angular/core';
import { fadeAnimation, slideInOutAnimation } from '../../animations';
import { User } from '../../models/user';

@Component({
  selector: 'app-edit-profile',
  standalone: true,
  imports: [],
  templateUrl: './edit-profile.component.html',
  styleUrl: './edit-profile.component.css',
  animations: [slideInOutAnimation, fadeAnimation],
})
export class EditProfileComponent {
  @Input() user!: User;
  @Output() userAct: EventEmitter<User> = new EventEmitter();
}
