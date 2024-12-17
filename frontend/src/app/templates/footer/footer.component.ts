import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css',
})
export class FooterComponent {
  @Output() view: EventEmitter<string> = new EventEmitter();

  home: boolean = true;
  orders: boolean = false;
  profile: boolean = false;

  viewEmit(selecction: string) {
    this.view.emit(selecction);
  }
}
