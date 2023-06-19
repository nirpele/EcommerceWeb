import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { Product } from "../Entitis.type";
import { getAllProducts } from "../Service/Service";


export const Main = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [searchTerm, setSearchTerm] = useState('');
  const [filteredProducts, setFilteredProducts] = useState<Product[]>([]);
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
  return (
    <div>
      <nav>
        <ul>
          <li>
          <Link to="/login">Login</Link>
          </li>
          <li>
            <a href="/register">Register</a>
          </li>
        </ul>
      </nav>

      <input
        type="text"
        placeholder="Search products..."
        value={searchTerm}
        onChange={handleSearch}
      />
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Model</th>
            <th>Price</th>
            <th>Category</th>
            <th>Amount</th>
          </tr>
        </thead>
        <tbody>
          {filteredProducts.map((product) => (
            <tr key={product.id}>
              <td>{product.name}</td>
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


export default Main