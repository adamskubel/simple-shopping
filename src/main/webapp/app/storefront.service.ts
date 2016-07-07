import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Product } from './product';

@Injectable()
export class StoreFrontService {

  private storeFrontUrl = 'store';  // URL to web api

  constructor(private http: Http) { }

  getProducts(): Promise<Product[]> {
    return this.http.get(this.storeFrontUrl)
               .toPromise()
               .then(response => response.json())
               .catch(this.handleError);
  }

  private handleError(error: any) {
   console.error('An error occurred', error);
   return Promise.reject(error.message || error);
 }

}
