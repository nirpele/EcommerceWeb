import React, { useContext, useEffect, useState } from 'react'
import { Product } from '../Entitis.type';
import { getAllCustomerProducts } from '../Service/Service';
import HomeCustomer from './HomeCustomer';
import { UserContext } from '../Service/UseContext/UserContext';
import { useNavigate } from 'react-router-dom';
import { ProductContext } from '../Service/UseContext/ProductContext';


const CustomerProducts = () => {
    const [products, setProducts] = useState<Product[]>([]);
    const [selectedProductId, setSelectedProductId] = useState<number | undefined>(undefined);
    const [wantToGetBack, SetWantToGetBack] = useState(false);
    const userContext=useContext(UserContext)
    const productContext = useContext(ProductContext);
    const navigate = useNavigate();
    useEffect(() => {
      const fetchProducts = async () => {
        const productList = await getAllCustomerProducts(userContext.user.token,userContext.user.id);
        
        setProducts(productList || []);
      };
      fetchProducts();
    }, []);
    useEffect(() => {
      productContext.setProduct({ id: selectedProductId });
    }, [selectedProductId]);

    const handleProductClick = (productId: number) => {
      setSelectedProductId(productId);
      productContext.setProduct({ id: selectedProductId });
      if(productContext.product.id !== undefined){
      navigate(`/oneProduct/${productId}`);}
    };

   const handleSetWantToGetBack=()=>{
        SetWantToGetBack(true)
    }
    if (selectedProductId !== undefined) {
      
    }
    if (wantToGetBack) {
    return <HomeCustomer/>;
  }
    return (
      <div>
       <h4>product that i purchase:</h4><br />
        <button type="button" onClick={handleSetWantToGetBack}> back </button><br />
        customer id: {userContext.user.id} <br />
        <table>
          <thead>
            <tr>
              <th>index: </th>
              <th>Name: </th>
              <th>Model: </th>
              <th>Price: </th>
              <th>Category: </th>
              <th>Amount: </th>
            </tr>
          </thead>
            <tbody>
              {products.map((product, index) => (
                <tr key={product.id }>
                  <td>{index + 1}</td>
                  <td>
                    <button type="button" onClick={() => handleProductClick(product.id!)}>
                      {product.name}
                    </button>
                  </td>
                  <td>{product.model}</td>
                  <td>{product.price}</td>
                  <td>{product.category}</td>
                  <td>{product.amount}</td>
                </tr>
              ))}
            </tbody>
          
        </table>
      </div>
    );
  };

export default CustomerProducts