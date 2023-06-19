package com.example.ecommerceWeb.entities;


import com.example.ecommerceWeb.enums.UserType;
import lombok.*;
import org.springframework.context.annotation.Scope;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Scope("prototype")
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    //uniqe
    private String email;
    private String password;
    @ManyToMany()
    List<Product> products =new ArrayList<>();

}
