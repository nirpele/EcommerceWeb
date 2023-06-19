package com.example.ecommerceWeb.entities;

import com.example.ecommerceWeb.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;

import javax.persistence.*;
import java.sql.Date;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.ORDINAL)
    private Category category;
    private String name;
    private String model;
    private String description;
    private Date startDate;
    private int amount;
    private double price;


    @PrePersist
    public void prePersist() {
        if (startDate == null) {
            startDate = new Date(System.currentTimeMillis());
        }
    }
}
