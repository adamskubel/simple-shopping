import { Product }                from './product';

export class Invoice {
  message: string;
  itemsBought: Product[];
  totalCost: number;
}
