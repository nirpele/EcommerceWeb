package com.example.ecommerceWeb.Controller;

import com.example.ecommerceWeb.Exceptions.CustomerException;
import com.example.ecommerceWeb.Exceptions.LoginException;
import com.example.ecommerceWeb.Exceptions.ProductException;
import com.example.ecommerceWeb.entities.Customer;
import com.example.ecommerceWeb.entities.Product;
import com.example.ecommerceWeb.enums.Category;
import com.example.ecommerceWeb.services.EcommerceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("")
@CrossOrigin
public class EcommerceWebController {
    private final EcommerceService ecommerceService;


    @GetMapping("products/getAllProductsByMaxPrice")
    public ResponseEntity<?> getAllProductsByMaxPrice(@RequestParam int maxPrice) {
        try {
            return new ResponseEntity<>(ecommerceService.getAllProductsByMaxPrice(maxPrice), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("products/getAllProductsByCategoryNameModel")
    public ResponseEntity<?> getAllProductsByCategoryNameModel(@RequestParam Category category, String name, String model) {
        try {
            return new ResponseEntity<>(ecommerceService.getAllProductsByCategoryNameModel(category, name, model), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("products/all")
    public ResponseEntity<?> getAllProducts() {
        try {
            return new ResponseEntity<>(ecommerceService.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("customers/all")
    public ResponseEntity<?> getAllCustomers(@RequestHeader(name = "Authorization") String token) throws LoginException {
        //check if token is valid
        try {
            return new ResponseEntity<>(ecommerceService.getAllCustomers(token), HttpStatus.OK);
        } catch (LoginException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("product/add")
    public ResponseEntity<?> addProduct(@RequestBody Product product, @RequestHeader(name = "Authorization") String token) throws LoginException, ProductException {
        //check if token is valid
        try {
            return new ResponseEntity<>(ecommerceService.addProduct(product, token), HttpStatus.OK);
        } catch (LoginException | ProductException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("product/delete")
    public ResponseEntity<?> deleteProduct(@RequestParam int productId, @RequestHeader(name = "Authorization") String token) throws LoginException, ProductException {
        try {
            //check if token is valid
            ecommerceService.deleteProduct(productId, token);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (LoginException | ProductException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("product/update")
    public ResponseEntity<?> updateProduct(@RequestBody Product product, @RequestHeader(name = "Authorization") String token) throws LoginException, ProductException {
        //check if token is valid
        try {
            ecommerceService.updateProduct(product, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (LoginException | ProductException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("customer/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) throws CustomerException {
        //check if token is valid
        try {
            ecommerceService.addCustomer(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (CustomerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("customer/delete")
    public ResponseEntity<?> deleteCustomer(@RequestParam int customerId, @RequestHeader(name = "Authorization") String token) throws LoginException, CustomerException {
        try {
            //check if token is valid
            ecommerceService.deleteCustomer(customerId, token);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (LoginException | CustomerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("customer/update")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader(name = "Authorization") String token) throws LoginException, CustomerException {
        //check if token is valid
        try {
            ecommerceService.updateCustomer(customer, token);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (LoginException | CustomerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("purchaseProduct")
    public ResponseEntity<?> customerPurchaseProduct(@RequestParam int customerId, @RequestParam int productId, @RequestHeader(name = "Authorization") String token) throws LoginException, CustomerException, ProductException {
        //check if token is valid
        try {
            ecommerceService.customerPurchaseProduct(customerId, productId, token);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (LoginException | CustomerException | ProductException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @PostMapping("purchaseProducts")
    public ResponseEntity<?> customerPurchaseProducts(@RequestParam int customerId, @RequestBody List<Product> products, @RequestHeader(name = "Authorization") String token) throws LoginException, CustomerException, ProductException {
        //check if token is valid
        try {
            ecommerceService.customerPurchaseProducts(customerId, products, token);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (LoginException | CustomerException | ProductException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("customers/getAllProducts")
    public ResponseEntity<?> getAllCustomerProducts(@RequestParam int customerId, @RequestHeader(name = "Authorization") String token) throws LoginException, CustomerException {
        try {
            return new ResponseEntity<>(ecommerceService.getAllCustomerProducts(customerId, token), HttpStatus.OK);
        } catch (LoginException | CustomerException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("findMyIdByEmail")
    public ResponseEntity<?> findMyIdByEmail(@RequestParam String email) throws CustomerException {
        try {
            return new ResponseEntity<>(ecommerceService.findMyIdByEmail(email), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @GetMapping("oneProduct")
    public ResponseEntity<?> getOneProductDetails(@RequestParam int productId) throws ProductException {
        try {
            return new ResponseEntity<>(ecommerceService.getOneProductDetails(productId), HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("oneCustomer")
    public ResponseEntity<?> getOneCustomerDetails(@RequestParam int customerId, @RequestHeader(name = "Authorization") String token) throws CustomerException, LoginException {
        try {
            return new ResponseEntity<>(ecommerceService.getOneCustomerDetails(customerId, token), HttpStatus.OK);
        } catch (CustomerException | LoginException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
