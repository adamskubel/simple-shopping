import { Component, OnInit } from '@angular/core';
import { Router }            from '@angular/router';

import { Product }                from './product';
import { CartService } from './cart.service';
import { Invoice } from './invoice';
import { InvoiceService } from './invoice.service';
import { CartInfo } from './cart.service';
import { FixedCurrencyPipe } from './fixed-currency.pipe';

@Component({
  selector: 'my-cart',
  templateUrl: 'app/cart.component.html',
  providers: [],
  pipes: [FixedCurrencyPipe]
})

export class CartComponent implements OnInit {
  products: Product[];
  invoice: Invoice;
  error: any;
  cartInfo:CartInfo;

  constructor(
    private router: Router,
    private cartService: CartService,
    private invoiceService: InvoiceService) {
    }

  getProducts() {
      this.products = this.cartService.getProducts();
  }

  removeProduct(product: Product) {

      this.cartService.delete(product);

      for (var index = 0;index < this.products.length;index++){
        if (this.products[index].productId == product.productId)
          break;
      }
      if (index > -1) {
           this.products.splice(index, 1);
      }
  }

  emptyCart(){
    this.cartService.empty();
  }

  updateProductQuantity(product:Product, newQuantity:number){
    if (newQuantity > 0)
      product.quantity = +newQuantity;

    this.cartService.update();
  }

  ngOnInit() {
    this.cartInfo = this.cartService.cartInfo;
    this.getProducts();
  }

  extractData(data:Invoice) {

  }

  checkout(){
    this.cartService.checkout(this.products)
    .then(invoice => {
      this.invoiceService.storeInvoice(invoice);
    this.router.navigate(['/invoice']);})
    .catch(error => this.error = error);
  }


}
