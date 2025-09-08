import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./articles/welcome/welcome.page').then((m) => m.WelcomePage),
  },
  {
    path: 'home',
    loadComponent: () =>
      import('./articles/home/home.page').then((m) => m.HomePage),
  },
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.routes').then((m) => m.AUTH_ROUTES),
  },
  {
    path: 'welcome',
    loadComponent: () =>
      import('./articles/welcome/welcome.page').then((m) => m.WelcomePage),
  },
  {
    path: 'profile',
    loadComponent: () =>
      import('./articles/profile/profile.page').then((m) => m.ProfilePage),
  },
  {
    path: 'shop-preview',
    loadComponent: () =>
      import('./shared/components/shop-preview/shop-preview.page').then(
        (m) => m.ShopPreviewPage
      ),
  },
];
