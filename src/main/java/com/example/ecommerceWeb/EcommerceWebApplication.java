package com.example.ecommerceWeb;


import com.example.ecommerceWeb.Utils.Art;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

@SpringBootApplication
public class EcommerceWebApplication {

	public static void main(String[] args) {
		System.out.println(new Date(System.currentTimeMillis()));
		SpringApplication.run(EcommerceWebApplication.class, args);
		System.out.println(Art.localhost);
	}

}
