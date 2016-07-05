import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Product }                from './product';
import { StoreFrontService }         from './storefront.service';
import { CartService } from './cart.service';

@Component({
  selector: 'my-products',
  templateUrl: 'app/product-list.component.html',
  styleUrls:  ['app/product-list.component.css']
})

export class ProductListComponent implements OnInit {
  products: Product[];
  error: any;

  constructor(
    private router: Router,
    private storeFrontService: StoreFrontService,
    private cartService: CartService) {
    }

  getProducts() {
    this.storeFrontService
        .getProducts()
        .then(products => {this.products = products})
        .catch(error => this.error = error);
  }

  addProductToCart(product:Product) {
    this.cartService.addProduct(product);
  }

  onSelect(product:Product){

  }

  ngOnInit() {
    this.getProducts();
  }


}
