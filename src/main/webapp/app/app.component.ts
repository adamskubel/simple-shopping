import { Component }          from '@angular/core';
import { ROUTER_DIRECTIVES }  from '@angular/router';

import { StoreFrontService }        from './storefront.service';
import { ProductListComponent } from './product-list.component';
import { CartService } from './cart.service';
import {CookieService} from 'angular2-cookie/core';

@Component({
  selector: 'my-app',

  template: `
    <h1>{{title}}</h1>
    <nav>
      <a [routerLink]="['/shopping']" routerLinkActive="active">Shopping</a>
      <a [routerLink]="['/cart']" routerLinkActive="active">Cart</a>
    </nav>
    <router-outlet></router-outlet>
  `,
  styleUrls: ['app/app.component.css'],
  directives: [ROUTER_DIRECTIVES],
  providers: [
    CookieService,
    StoreFrontService,
    CartService
  ]
})
export class AppComponent {
  title = "Adam's Store";
}
