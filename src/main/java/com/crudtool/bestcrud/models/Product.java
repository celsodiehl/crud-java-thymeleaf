package com.crudtool.bestcrud.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "products")
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "O nome do produto é obrigatório")
    private String name;
    private String brand;
    private String category;
    private double price;

    @Column(columnDefinition = "TEXT")
    private String description;
    private Date createdAt;
    private String imageFileName;


}
