import { Component, inject } from '@angular/core';
import { IDepartment } from '../../shared/model/departments.model';
import { TabComponent } from '../../shared/components/tabs/tab/tab.component';
import { TabsetComponent } from '../../shared/components/tabs/tabset/tabset.component';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-departments',
  imports: [TabComponent, TabsetComponent],
  templateUrl: './departments.component.html',
  styleUrl: './departments.component.scss',
})
export class DepartmentsComponent {
  readonly #router: Router = inject(Router);
  readonly #route: ActivatedRoute = inject(ActivatedRoute);

  readonly departments: Array<IDepartment> = [
    {
      id: 0,
      name: 'IT',
      employees: [
        {
          id: 0,
          firstName: 'John',
          lastName: 'Doe',
        },
        {
          id: 1,
          firstName: 'Pierre',
          lastName: 'Bauer',
        },
        {
          id: 2,
          firstName: 'Fred',
          lastName: 'Stravazki',
        },
      ],
    },
    {
      id: 0,
      name: 'Legal',
      employees: [
        {
          id: 3,
          firstName: 'Paul',
          lastName: 'Bernard',
        },
      ],
    },
  ];

  goToEmployeeDetails(id: number) {
    this.#router.navigate(['employee', id], { relativeTo: this.#route });
  }
}
