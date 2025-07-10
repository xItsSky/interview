import { Component, signal } from '@angular/core';

@Component({
  selector: 'app-root',
  imports: [],
  template: `
    <main id="frontend-application">
      <section id="frontend-application__container">
        <h1>Employees Application</h1>
        <app-root></app-root>
      </section>
    </main>
  `,
  styles: [
    `
      #frontend-application {
        height: 100%;
        width: 100%;
        display: flex;
        flex-direction: column;

        &__container {
          padding: 1rem;
        }
      }
    `,
  ],
})
export class App {
  protected readonly title = signal('frontend');
}
