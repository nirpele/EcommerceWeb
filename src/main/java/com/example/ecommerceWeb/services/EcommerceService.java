package com.example.ecommerceWeb.services;

import com.example.ecommerceWeb.Exceptions.CustomerException;
import com.example.ecommerceWeb.Exceptions.LoginException;
import com.example.ecommerceWeb.Exceptions.ProductException;
import com.example.ecommerceWeb.Login.UserDetails;
import com.example.ecommerceWeb.Repositories.CustomerRepo;
import com.example.ecommerceWeb.Repositories.ProductRepo;
import com.example.ecommerceWeb.Utils.JWTUtil;
import com.example.ecommerceWeb.entities.Customer;
import com.example.ecommerceWeb.entities.Product;
import com.example.ecommerceWeb.enums.Category;
import com.example.ecommerceWeb.enums.UserType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
@Data
public class EcommerceService {
    private final ProductRepo productRepo;
    private final CustomerRepo customerRepo;
    private final JWTUtil jwtUtil;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<Product> getAllProductsByCategoryNameModel(Category category, String name, String model) {
        return productRepo.findAll().stream().filter(product -> product.getCategory().equals(category) && product.getName().equals(name) && product.getModel().equals(model)).collect(Collectors.toList());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<Product> getAllProductsByMaxPrice(int maxPrice) {
        return productRepo.findAll().stream().filter(product -> product.getPrice() <= maxPrice).collect(Collectors.toList());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<Customer> getAllCustomers(String token) throws LoginException {
        boolean isValid = jwtUtil.validateToken(token, new UserDetails("admin", "admin@admin", UserType.ADMINISTRATOR));
        if (isValid) {
            return customerRepo.findAll();
        } else {
            throw new LoginException("mail or password are wrong or empty please try again. ");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Product addProduct(Product product, String token) throws LoginException, ProductException {
        boolean isValid = jwtUtil.validateToken(token, new UserDetails("admin", "admin@admin", UserType.ADMINISTRATOR));
        if (isValid) {
            if (!productRepo.existsById(product.getId()) && !product.getName().equals("") && product.getCategory() != null && !product.getModel().equals("") && !product.getDescription().equals("") && product.getPrice() > 0 && product.getAmount() > 0) {
                productRepo.save(product);
                List<Product> filterListProduct = productRepo.findByNameLike(product.getName()).stream().filter(product1 -> product1.getModel().equals(product.getModel())).collect(Collectors.toList());
                if (filterListProduct.size() == 1) {
                    return filterListProduct.get(0);
                } else {
                    throw new ProductException("the product already exist.");
                }
            } else {
                throw new ProductException("one of parameters are empty please fill all the pram or the product already exist.");
            }
        } else {
            throw new LoginException("mail or password are wrong or empty please try again.");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteProduct(int productId, String token) throws LoginException, ProductException {
        boolean isValid = jwtUtil.validateToken(token, new UserDetails("admin", "admin@admin", UserType.ADMINISTRATOR));
        if (isValid) {
            if (!productRepo.existsById(productId)) {
                throw new ProductException("this company not exist try again or one of the parameters are empty.");
            } else {
                productRepo.deleteById(productId);
            }
        } else {
            throw new LoginException("mail or password are wrong please try again.");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateProduct(Product product, String token) throws LoginException, ProductException {
        boolean isValid = jwtUtil.validateToken(token, new UserDetails("admin", "admin@admin", UserType.ADMINISTRATOR));
        if (isValid) {
            if (productRepo.existsById(product.getId()) && !product.getName().equals("") && product.getCategory() != null && !product.getModel().equals("") && !product.getDescription().equals("") && product.getPrice() > 0 && product.getAmount() > 0) {
                product.setStartDate(productRepo.findProductById(product.getId()).getStartDate());
                productRepo.saveAndFlush(product);
            } else {
                throw new ProductException("this product not exist try again.");
            }
        } else {
            throw new LoginException("mail or password are wrong please try again.");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Product getOneProductDetails(int productId) throws ProductException {
        if (productRepo.existsById(productId)) {
            return productRepo.findProductById(productId);
        } else {
            throw new ProductException(" this product id not found please check this product. ");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //customer service;
    public void addCustomer(Customer customer) throws CustomerException {
        if (!customer.getEmail().isEmpty() && !customer.getPassword().isEmpty() && !customer.getFirstName().isEmpty() && !customer.getLastName().isEmpty()) {
            List<Customer> customerCheckEmail = customerRepo.findByEmail(customer.getEmail());
            if (customerCheckEmail.isEmpty()) {
                customerRepo.save(customer);
            } else {
                throw new CustomerException("Email already exists.");
            }
        } else {
            throw new CustomerException("Email, password, first name, or last name are empty. Please try again.");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void deleteCustomer(int customerId, String token) throws LoginException, CustomerException {
        boolean isValid = jwtUtil.validateToken(token, new UserDetails(customerRepo.findCustomerById(customerId).getPassword(), customerRepo.findCustomerById(customerId).getEmail(), UserType.CUSTOMER));
        if (isValid) {
            if (!customerRepo.existsById(customerId)) {
                throw new CustomerException("Email already exists.");
            } else {
                customerRepo.deleteById(customerId);
            }
        } else {
            throw new LoginException("mail or password are wrong please try again");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void updateCustomer(Customer customer, String token) throws LoginException, CustomerException {
        //the userDetails hold the old email and password and the new customer have wrong details for login
        //so we bring from the repo the old details to get in.
        String oldPassword = customerRepo.findById(customer.getId()).get().getPassword();
        String oldEmail = customerRepo.findById(customer.getId()).get().getEmail();
        boolean isValid = jwtUtil.validateToken(token, new UserDetails(oldPassword, oldEmail, UserType.CUSTOMER));
        if (isValid) {
            if (!customer.getEmail().equals("") && !customer.getPassword().equals("") && !customer.getFirstName().equals("") && !customer.getFirstName().equals("") && customerRepo.findByEmail(customer.getEmail()).isEmpty()) {
                List<Product> products = customerRepo.findById(customer.getId()).get().getProducts();
                customer.setProducts(products);
                customerRepo.saveAndFlush(customer);
            } else {
                throw new CustomerException("mail or password or name are empty or Email already exists please try again.");
            }
        } else {
            throw new LoginException("mail or password are wrong please try again");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void customerPurchaseProduct(int customerId, int productId, String token) throws LoginException, CustomerException, ProductException {
        Optional<Customer> customer = customerRepo.findById(customerId);
        Customer customerData = customer.get();
        boolean isValid = jwtUtil.validateToken(token, new UserDetails(customerData.getPassword(), customerData.getEmail(), UserType.CUSTOMER));
        if (isValid) {
            if (productRepo.existsById(productId)) {
                Product product = productRepo.findProductById(productId);
                if (product.getAmount() > 0) {
                    product.setAmount(product.getAmount() - 1);
                    customerData.getProducts().add(product);
                    customerRepo.save(customerData);
                } else {
                    throw new CustomerException("out of stock please connect with the customer for more details. ");
                }
            } else {
                throw new ProductException("product id not exist. ");
            }
        } else {
            throw new LoginException("mail or password are wrong please try again. ");
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<Product> getAllCustomerProducts(int customerId, String token) throws LoginException, CustomerException {
        Optional<Customer> customer = customerRepo.findById(customerId);
        Customer customerData = customer.get();
        boolean isValid = jwtUtil.validateToken(token, new UserDetails(customerData.getPassword(), customerData.getEmail(), UserType.CUSTOMER));
        if (isValid) {
            if (customerRepo.existsById(customerId)) {
                return customerData.getProducts();
            } else {
                throw new CustomerException("Customer id not exist.");
            }
        } else {
            throw new LoginException("mail or password are wrong please try again. ");
        }
    }

    public int findMyIdByEmail(String email) throws CustomerException {
        List<Customer> customers = customerRepo.findAll().
                stream().filter(customer -> customer.getEmail().equals(email)).
                collect(Collectors.toList());
        if (customers.size() == 1) {
            return customers.get(0).getId();
        } else {
            throw new CustomerException("the id customer not exist please check what wrong");
        }
    }

    public void customerPurchaseProducts(int customerId, List<Product> products, String token) throws LoginException, CustomerException, ProductException {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            Customer customerData = customer.get();
            boolean isValid = jwtUtil.validateToken(token, new UserDetails(customerData.getPassword(), customerData.getEmail(), UserType.CUSTOMER));
            if (isValid) {
                for (Product product : products) {
                    Optional<Product> productOptional = productRepo.findById(product.getId());
                    if (productOptional.isPresent()) {
                        Product fetchedProduct = productOptional.get();
                        if (fetchedProduct.getAmount() > 0) {
                            fetchedProduct.setAmount(fetchedProduct.getAmount() - 1);
                            customerData.getProducts().add(fetchedProduct);
                        } else {
                            throw new CustomerException("Out of stock. Please contact the customer for more details.");
                        }
                    } else {
                        throw new ProductException("Product ID does not exist: " + product.getId());
                    }
                }
                customerRepo.save(customerData);
            } else {
                throw new LoginException("Invalid credentials. Please try again.");
            }
        } else {
            throw new CustomerException("Customer not found with ID: " + customerId);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Customer getOneCustomerDetails(int customerId, String token) throws CustomerException,LoginException {
        Optional<Customer> customer = customerRepo.findById(customerId);
        if (customer.isPresent()) {
            Customer customerData = customer.get();
            boolean isValid = jwtUtil.validateToken(token, new UserDetails(customerData.getPassword(), customerData.getEmail(), UserType.CUSTOMER));
            if (isValid) {
                return customerRepo.findCustomerById(customerId);
            } else {
                throw new LoginException("mail or password are wrong please try again. ");
            }
        } else {
            throw new CustomerException(" this customer id not found please check this customer. ");
        }
    }
}