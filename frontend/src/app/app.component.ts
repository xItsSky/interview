import { Component, signal } from '@angular/core';
import { HeaderComponent } from './core/components/header/header.component';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [HeaderComponent, RouterOutlet],
  template: `
    <main id="frontend-application">
      <lia-header />
      <section id="frontend-application__container">
        <router-outlet></router-outlet>
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

        lia-header {
          height: 4rem;
        }

        &__container {
          padding: 1rem;
        }
      }
    `,
  ],
})
export class AppComponent {
  protected readonly title = signal('frontend');
}
