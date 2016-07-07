import { Component, Input, OnInit} from '@angular/core';
import { Product }        from './product';
import { Invoice }  from './invoice';
import { InvoiceService } from './invoice.service';
import { FixedCurrencyPipe } from './fixed-currency.pipe';

@Component({
  selector: 'invoice-detail',
  templateUrl: 'app/invoice-detail.component.html',
  pipes: [FixedCurrencyPipe]
})

export class InvoiceDetailComponent implements OnInit {

  @Input() invoice: Invoice;

  constructor(private invoiceService:InvoiceService) {
  }

  ngOnInit()
  {
    this.invoice = this.invoiceService.getLatestInvoice();
  }
}


/*
Copyright 2016 Google Inc. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
