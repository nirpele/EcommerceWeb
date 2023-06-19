import {combineReducers, createStore} from "redux";
import { customerReducer } from "./CustomerState";
import { ProductState, productReducer } from "./ProductState";

//single reducer
//const store = createStore(carReducer);
//store.getState().cars

//multiplate reducers
const reducers = combineReducers({
    customerState:customerReducer,
    productState:productReducer
    
})
const store = createStore(reducers);

export default store;

