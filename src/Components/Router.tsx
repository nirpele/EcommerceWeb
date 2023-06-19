import { Route, Routes } from 'react-router';
import { BrowserRouter } from 'react-router-dom';
import NotFound from '../Pages/NotFound';
import LogIn from '../Pages/LogIn';
import Main from '../Pages/Main';
import Register from '../Pages/Register';
import HomeCustomer from '../Pages/HomeCustomer';
import HomeAdmin from '../Admin/HomeAdmin';
import GetOneProduct from '../Pages/GetOneProduct';
import { NavBar } from './NavBar';



function Router() {
  
  return (
    <div className="Router">
      <div><NavBar/></div>
      <BrowserRouter>
        <Routes>admin
          <Route path={`/`} element={<Main />} />
          <Route path={`/login`} element={<LogIn />} />
          <Route path={`/customer/:selectedCustomer/`} element={<HomeCustomer/>} />
          <Route path={`/admin/*`} element={<HomeAdmin />} />
          <Route path={`/oneProduct/:selectedProductId`} element={<GetOneProduct/>}/>
          <Route path={`/register`} element={<Register />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default Router;
