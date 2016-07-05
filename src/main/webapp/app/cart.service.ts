import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import {CookieService} from 'angular2-cookie/core';

import { Product } from './product';

@Injectable()
export class CartService {

  constructor(private _cookieService:CookieService) { }

  addProduct(product: Product) {

    let cartData:Product[] = this._cookieService.getObject("cartData") as Product[];
    if (!cartData)
      cartData = new Array<Product>();

    cartData.push(product);

    this._cookieService.putObject("cartData",cartData);
  }

  getProducts() : Product[]{
    return this._cookieService.getObject("cartData") as Product[];
  }

  delete(product: Product)
  {
      let cartData:Product[] = this._cookieService.getObject("cartData") as Product[];
      if (!cartData)
      {
        console.error("Cart is already empty");
        return;
      }

      var index = 0;
      for (;index < cartData.length;index++){
        if (cartData[index].productId == product.productId)
          break;
      }

      if (index > -1) {
           cartData.splice(index, 1);
           this._cookieService.putObject("cartData",cartData);
      }
      else
        console.error("Product " + product.name + " not found in cart");
  }

  private handleError(error: any) {
   console.error('An error occurred', error);
 }

}
