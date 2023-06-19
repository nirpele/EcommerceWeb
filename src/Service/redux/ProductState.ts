import {  Product } from '../../Entitis.type';



//our redux state
export class ProductState{
    public products:Product[] = [];
}

//action types
export enum ProductActionType{
    ProductsDownloaded = "ProductsDownloaded",
    ProductAdd = "ProductAdded",
    ProductUpdated = "ProductUpdated",
    ProductDelete = "ProductDelete",
}


//action decleration
export interface ProductAction{
    type:ProductActionType,
    payload?: any,
}

//action functions
export function productDownloadedAction(products : Product[]|undefined):ProductAction{
    return {type: ProductActionType.ProductsDownloaded, payload:products }
}

export function productAddAction(product :Product):ProductAction{
    return {type: ProductActionType.ProductAdd, payload: product}
}

export function productUpdateAction(product : Product):ProductAction{
    return {type: ProductActionType.ProductUpdated, payload: product}
}

export function productDeleteAction(id:number):ProductAction{
    return{type: ProductActionType.ProductDelete, payload: id}
}

//reducer
export function productReducer(currentState: ProductState = new ProductState(), action:ProductAction):ProductState{
    const newState = {...currentState}; //spread variable (...)

    switch (action.type){
        case ProductActionType.ProductsDownloaded:
            newState.products = action.payload;
            break;
        case ProductActionType.ProductAdd:
            //do something
            break;
        case ProductActionType.ProductUpdated:
            //do something
            break;
        case ProductActionType.ProductDelete:
            //do something
            break;
    }

    return newState;
}
