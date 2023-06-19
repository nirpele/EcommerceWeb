import { useState, useEffect, useContext } from "react";
import { Product } from "../Entitis.type";
import { getAllProducts } from "../Service/Service";
import { useNavigate } from "react-router-dom";
import CustomerProducts from "./CustomerProducts";
import { UserContext } from "../Service/UseContext/UserContext";
import { ProductContext } from "../Service/UseContext/ProductContext";

const HomeCustomer = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);
  const [selectedProductId, setSelectedProductId] = useState<number | undefined>(undefined);
  const [showCustomerProducts, setShowCustomerProducts] = useState(false);
  const userContext = useContext(UserContext);
  const productContext = useContext(ProductContext);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProducts = async () => {
      const productList = await getAllProducts();
      setProducts(productList || []);
    };
    fetchProducts();
  }, []);

  useEffect(() => {
    productContext.setProduct({ id: selectedProductId });
  }, [selectedProductId]);

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    const searchTerm = event.target.value;
    setSearchTerm(searchTerm);

    const filteredProducts = products.filter((product) =>
      product.name.toLowerCase().includes(searchTerm.toLowerCase())
    );
    setFilteredProducts(filteredProducts);
  };

  const handleProductClick = (productId: number) => {
    setSelectedProductId(productId);
    productContext.setProduct({ id: selectedProductId });
    if(productContext.product.id !== undefined){
    navigate(`/oneProduct/${productId}`);}
  };

  const handleCustomerProducts = () => {
    setShowCustomerProducts(true);
  };

  if (showCustomerProducts) {
    return <CustomerProducts />;
  }

  return (
    <div>
      <button type="button" onClick={()=>navigate('/')}> back </button><br />
      <button type="button" onClick={handleCustomerProducts}>
        My purchases
      </button>
      <br />
      <input
        type="text"
        placeholder="Search products..."
        value={searchTerm}
        onChange={handleSearch}
      />
      <br />
      customer id: {userContext.user.id}
      <br />
      <table>
        <thead>
          <tr>
            <th>index</th>
            <th>Name</th>
            <th>Model</th>
            <th>Price</th>
            <th>Category</th>
            <th>Amount</th>
          </tr>
        </thead>
        {filteredProducts.length === 0 ? (
          <tbody>
            <tr>
              <td colSpan={5}>No products found.</td>
            </tr>
          </tbody>
        ) : (
          <tbody>
            {filteredProducts.map((product, index) => (
              <tr key={product.id}>
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
        )}
      </table>
    </div>
  );
};

export default HomeCustomer;
