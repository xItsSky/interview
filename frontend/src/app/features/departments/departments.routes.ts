import { Departments } from './departments';

export const DEPARTMENTS_ROUTES = [
  {
    path: '',
    children: [{ path: '', component: Departments }],
  },
];
