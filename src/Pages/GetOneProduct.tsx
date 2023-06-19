import { useContext, useEffect, useState } from "react";
import { Product } from "../Entitis.type";
import { getOneProduct, purchaseOneProduct } from "../Service/Service";
import HomeCustomer from "./HomeCustomer";
import { UserContext } from "../Service/UseContext/UserContext";
import { ProductContext } from "../Service/UseContext/ProductContext";
import { useNavigate } from "react-router-dom";



const GetOneProduct = () => {
  const userContext=useContext(UserContext)
  const [product, setProduct] = useState<Product | undefined>({
    id: 0,
    name: "",
    model: "",
    description: "",
    price: 0,
    amount: 0,
    category: "",
  });

  const [purchased, setPurchased] = useState(false);
  const productContext=useContext(ProductContext)
  const navigate = useNavigate();
  
  useEffect(() => {
    const fetchProduct = async () => {
      const productData = await getOneProduct(productContext.product.id);
      setProduct(productData);
    };
    fetchProduct();
  }, []);

  const handlePurchase = async () => {
    try {
      if (product && product.id) {

        const resultPurchase = await purchaseOneProduct(userContext.user.token,userContext.user.id,productContext.product.id);
        if (resultPurchase === 1) {
          alert("Purchase successful!");
          setPurchased(true);
        }
        else{
          alert("Purchase faild look if the product out of stock");       
          setPurchased(true);
        }
      } else {
        throw new Error("Product ID is missing.");
      }
    } catch (error) {
      alert("Purchase failed!");
      setPurchased(true);
    }
  };

  if (purchased) {
    navigate(`/customer/${userContext.user.id}`)
  }

  return (
    <div>
      <button type="button" onClick={()=>navigate(-1)}> back </button><br />
      <table>
        <tbody>
          <tr>
            <td>Product ID: </td>
            <td>{product?.id}</td>
          </tr>
          <tr>
            <td>Category: </td>
            <td>{product?.category}</td>
          </tr>
          <tr>
            <td>Name: </td>
            <td>{product?.name}</td>
          </tr>
          <tr>
            <td>Model: </td>
            <td>{product?.model}</td>
          </tr>
          <tr>
            <td>Description: </td>
            <td>{product?.description}</td>
          </tr>
          <tr>
            <td>Price: </td>
            <td>{product?.price}</td>
          </tr>
          <tr>
            <td>Amount: </td>
            <td>{product?.amount}</td>
          </tr>
        </tbody>
      </table>
      <button onClick={handlePurchase}>Purchase</button>
    </div>
  );
};

export default GetOneProduct;
