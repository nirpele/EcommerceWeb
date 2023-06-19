import { Customer } from '../../Entitis.type';



//our redux state
export class CusomerState{
    public customers:Customer[] = [];
}

//action types
export enum CustomerActionType{
    CustomersDownloaded = "CustomersDownloaded",
    CustomerAdd = "CustomerAdded",
    CustomerUpdated = "CustomerUpdated",
    CustomerDelete = "CustomerDelete",
}


//action decleration
export interface CustomerAction{
    type:CustomerActionType,
    payload?: any,
}

//action functions
export function customerDownloadedAction(customers : Customer[]):CustomerAction{
    return {type: CustomerActionType.CustomersDownloaded, payload: customers}
}

export function customerAddAction(customer :Customer):CustomerAction{
    return {type: CustomerActionType.CustomerAdd, payload: customer}
}

export function customerUpdateAction(customer : Customer):CustomerAction{
    return {type: CustomerActionType.CustomerUpdated, payload: customer}
}

export function customerDeleteAction(id:number):CustomerAction{
    return{type: CustomerActionType.CustomerDelete, payload: id}
}

//reducer
export function customerReducer(currentState: CusomerState = new CusomerState(), action:CustomerAction):CusomerState{
    const newState = {...currentState}; //spread variable (...)

    switch (action.type){
        case CustomerActionType.CustomersDownloaded:
            newState.customers = action.payload;
            break;
        case CustomerActionType.CustomerAdd:
            //do something
            break;
        case CustomerActionType.CustomerUpdated:
            //do something
            break;
        case CustomerActionType.CustomerDelete:
            //do something
            break;
    }

    return newState;
}


