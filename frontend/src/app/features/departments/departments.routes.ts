import { DepartmentsComponent } from './departments.component';
import { Routes } from '@angular/router';

export const DEPARTMENTS_ROUTES: Routes = [
  {
    path: '',
    children: [{ path: '', component: DepartmentsComponent }],
  },
];
