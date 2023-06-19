import { useState, useEffect, useContext } from "react";
import { Product } from "../Entitis.type";
import { getAllProducts } from "../Service/Service";
import { UserContext } from "../Service/UseContext/UserContext";
import GetOneProduct from "../Pages/GetOneProduct";
import { ProductContext } from "../Service/UseContext/ProductContext";
import { useNavigate } from "react-router-dom";

const HomeAdmin = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [searchTerm, setSearchTerm] = useState("");
  const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);
  const [selectedProductId, setSelectedProductId] = useState<number | undefined>(undefined);
  const productContext = useContext(ProductContext);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProducts = async () => {
      const productList = await getAllProducts();
      setProducts(productList || []);
    };
    fetchProducts();
  }, []);

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
  };
  const handleUpdateProduct = (event: React.ChangeEvent<HTMLInputElement>) => {
    const updateProduct = event.target.value;
  };


  if (selectedProductId !== undefined) {
    const selectedProduct = products.find((product) => product.id === selectedProductId);
    if (selectedProduct) {
      productContext.setProduct({ id: selectedProductId });
      if(productContext.product.id !== undefined){
      navigate(`/oneProduct/${productContext.product.id}`);}
    }
  }

  return (
    <div>
      <input
        type="text"
        placeholder="Search products..."
        value={searchTerm}
        onChange={handleSearch}
      /><br />
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
            {products.map((product, index) => (
              <tr key={product.id || index}>
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

export default HomeAdmin;
