import { Component, OnInit }          from '@angular/core';
import { ROUTER_DIRECTIVES }  from '@angular/router';

import { StoreFrontService }        from './storefront.service';
import { ProductListComponent } from './product-list.component';
import { InvoiceDetailComponent } from './invoice-detail.component';
import { CartComponent } from './cart.component';
import { CartService } from './cart.service';
import { CookieService } from 'angular2-cookie/core';
import { InvoiceService } from './invoice.service';
import { CartInfo } from './cart.service';

@Component({
  selector: 'my-app',

  template: `
  <nav class="navbar navbar-inverse">
    <div class="container-fluid">
      <div class="navbar-header">
        <a class="navbar-brand" href="#">{{title}}</a>
      </div>
      <ul class="nav navbar-nav">
        <li class="nav-item"><a [routerLink]="['/shopping']" routerLinkActive="active">Store</a></li>
        <li class="nav-item"><a  [routerLink]="['/cart']" routerLinkActive="active"><span class="glyphicon glyphicon-shopping-cart"></span>&nbsp;Cart&nbsp;<span class="badge">{{cartInfo.itemCount}}</span></a></li>
      </ul>
    </div>
  </nav>
  <router-outlet></router-outlet>
  `,
  styleUrls: ['node_modules/bootstrap/dist/css/bootstrap.min.css'],
  directives: [ROUTER_DIRECTIVES],
  providers: [
    CookieService,
    StoreFrontService,
    CartService,
    InvoiceService
  ],
  precompile:[ProductListComponent,CartComponent,InvoiceDetailComponent]
})
export class AppComponent implements OnInit {
  title = "Adam's Store";

  private cartInfo:CartInfo;

  constructor(private cartService:CartService){}

  ngOnInit(){
    console.log("Init app");
    this.cartInfo = this.cartService.cartInfo;
  }
}
