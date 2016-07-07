import { provideRouter, RouterConfig }  from '@angular/router';

import { ProductListComponent } from './product-list.component';
import { CartComponent } from './cart.component';
import { InvoiceDetailComponent } from './invoice-detail.component';

export const routes: RouterConfig = [
   {
      path: '',
      redirectTo: '/shopping',
      pathMatch: 'full'
  },
  {
    path: 'shopping',
    component: ProductListComponent
  },
  {
    path: 'cart',
    component: CartComponent
  },
  {
    path: 'invoice',
    component: InvoiceDetailComponent
  }
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(routes)
];
