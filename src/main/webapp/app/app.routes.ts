import { provideRouter, RouterConfig }  from '@angular/router';

import { ProductListComponent } from './product-list.component';
import { CartComponent } from './cart.component';

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
  }
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(routes)
];
