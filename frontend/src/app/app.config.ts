import {
  ApplicationConfig,
  provideBrowserGlobalErrorListeners,
  provideZoneChangeDetection,
} from '@angular/core';
import { provideRouter, Routes, withHashLocation } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';

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
    provideRouter(ROUTES, withHashLocation()),
    provideBrowserGlobalErrorListeners(),
  ],
};
