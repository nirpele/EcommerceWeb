import React from 'react';
import ReactDOM from 'react-dom/client';

import Router from './Components/Router';
import { UserContextProvider } from './Service/UseContext/UserContext';
import { ProductContextProvider } from './Service/UseContext/ProductContext';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    
      <UserContextProvider>
        <ProductContextProvider>
        <Router />
        </ProductContextProvider>
      </UserContextProvider>
    
  </React.StrictMode>
);


