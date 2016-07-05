import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Product }                from './product';
import { CartService } from './cart.service';

@Component({
  selector: 'my-cart',
  templateUrl: 'app/cart.component.html',
  styleUrls:  ['app/cart.component.css']
})

export class CartComponent implements OnInit {
  products: Product[];
  error: any;

  constructor(
    private router: Router,
    private cartService: CartService) {
    }

  getProducts() {
      this.products = this.cartService.getProducts();
  }

  removeProduct(product: Product) {

      this.cartService.delete(product);
      this.getProducts();
  }

  close() {

  }

  ngOnInit() {
    this.getProducts();
  }

  checkout(){

  }


}
