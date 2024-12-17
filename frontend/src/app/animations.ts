import {
  animate,
  keyframes,
  style,
  transition,
  trigger,
} from '@angular/animations';

export const slideInOutAnimation = trigger('slideInOut', [
  transition(':enter', [
    style({ transform: 'translateX(100%)' }),
    animate('400ms ease-in', style({ transform: 'translateX(0%)' })),
  ]),
]);

export const fadeAnimation = trigger('fadeIn', [
  transition(':enter', [
    style({ opacity: 0 }),
    animate('400ms ease-in', style({ opacity: 1 })),
  ]),
]);
