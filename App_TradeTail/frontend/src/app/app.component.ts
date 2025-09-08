import { Component, EnvironmentInjector, HostBinding } from '@angular/core';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';
import { OverlayContainer } from '@angular/cdk/overlay';
import { addIcons } from 'ionicons';
import * as all from 'ionicons/icons';
import { HttpClientModule } from '@angular/common/http';
import { IonicStorageModule } from '@ionic/storage-angular';
@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  imports: [IonApp, IonRouterOutlet, HttpClientModule, IonicStorageModule],
})
export class AppComponent {
  @HostBinding('class') componentCssClass: any;

  constructor(public overlayContainer: OverlayContainer) {
    addIcons(all);
  }

  public onSetTheme(e: string) {
    this.overlayContainer.getContainerElement().classList.add(e);
    this.componentCssClass = e;
  }
}
