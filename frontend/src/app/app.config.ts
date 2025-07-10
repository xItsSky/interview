import {
  ApplicationConfig,
  provideBrowserGlobalErrorListeners,
  provideZoneChangeDetection,
} from '@angular/core';
import { provideRouter, Routes, withHashLocation } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

export const ROUTES: Routes = [
  {
    path: '',
    redirectTo: 'departments',
    pathMatch: 'full',
  },
  {
    path: 'departments',
    loadChildren: async () =>
      (await import('./features/departments/departments.routes'))
        .DEPARTMENTS_ROUTES,
  },
];

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection(),
    provideHttpClient(),
    provideAnimationsAsync(),
    provideRouter(ROUTES, withHashLocation()),
    provideBrowserGlobalErrorListeners(),
  ],
};
