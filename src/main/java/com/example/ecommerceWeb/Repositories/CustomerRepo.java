package com.example.ecommerceWeb.Repositories;


import com.example.ecommerceWeb.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {

    Customer findCustomerById(int id);

    List<Customer> findByEmail(String email);
}
