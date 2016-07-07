import { Injectable }    from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';

import 'rxjs/add/operator/toPromise';
import {CookieService} from 'angular2-cookie/core';

import { Product } from './product';
import { Invoice } from './invoice';

export interface CartInfo {
   itemCount:number;
   subTotal:number;
}


@Injectable()
export class CartService  {

  constructor(private _cookieService:CookieService, private http: Http) {
    this.update();
  }

  private checkoutUrl = '/checkout';  // URL to web api
  private cookieKey = 'cartData';
  cartInfo:CartInfo = {itemCount:0,subTotal:0};
  private cartData:Product[];

  getProducts():Product[]
  {
    if (!this.cartData){
      console.log("Reading cookie");
      this.cartData = this._cookieService.getObject(this.cookieKey) as Product[];
      if (!this.cartData)
        this.cartData = new Array<Product>();
    }
    return this.cartData;
  }

  private indexOf(product:Product)  {
    let cartData:Product[] = this.getProducts();
    for (var i=0;i < cartData.length;i++){
      if (cartData[i].productId == product.productId)
        return i;
    }
    return -1;
  }

  update(){
      let cartData:Product[] = this.getProducts();
      if (cartData)
      {
          let itemCount:number = 0;
          let subTotal:number = 0;
          for (var i=0;i < cartData.length;i++)
          {
            itemCount += +cartData[i].quantity;
            subTotal += cartData[i].price * cartData[i].quantity;
          }
          this.cartInfo.itemCount = itemCount;
          this.cartInfo.subTotal = subTotal;
      }
      this.writeCookie();
  }

  writeCookie(){
      this._cookieService.putObject(this.cookieKey,this.cartData);
  }

  addProduct(product: Product, quantity:number) {

    let cartData:Product[] = this.getProducts();
    if (!cartData)
      cartData = new Array<Product>();

    var index = this.indexOf(product);

    if (index > 0) {
      cartData[index].quantity++;
    }
    else
    {
      if (quantity > 1)
        product.quantity = quantity;
      else
        product.quantity = 1;

      cartData.push(product);
    }

    this.update();
  }

  delete(product: Product)
  {
      let cartData:Product[] = this.getProducts();
      var index = this.indexOf(product);

      if (index < cartData.length) {
           cartData.splice(index, 1);
      }

      this.update();
  }

  empty()
  {
    this.cartData.length = 0;
    this.update();
  }

  checkout(products:Product[])
  {
    let body = JSON.stringify(products);
    let headers = new Headers({ 'Content-Type': 'application/json' });
    let options = new RequestOptions({ headers: headers });

    return this.http.post(this.checkoutUrl,body,options)
               .toPromise()
               .then(response => {
                 this.empty();
                 return response.json();})
               .catch(this.handleError);
  }

private handleError(error: any) {
   console.error('An error occurred', error);
 }

}
