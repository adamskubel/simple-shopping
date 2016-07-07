import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Invoice } from './invoice';

@Injectable()
export class InvoiceService {

  constructor(private http: Http) { }

  invoiceList:Invoice[];

  getLatestInvoice(): Invoice {
    return this.invoiceList[this.invoiceList.length - 1];
  }

  storeInvoice(invoice:Invoice){
    if (!this.invoiceList) this.invoiceList = new Array<Invoice>();
    this.invoiceList.push(invoice);
  }

}
