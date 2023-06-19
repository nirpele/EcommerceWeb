export interface Product {
    id: number|undefined;
    name: string;
    model: string;
    description: string;
    price: number;
    category: string;
    amount:number;
  }

  export interface Customer {
    id: number|undefined;
    firstName: string;
    lastName: string;
    email: string;
    password:string;
  }