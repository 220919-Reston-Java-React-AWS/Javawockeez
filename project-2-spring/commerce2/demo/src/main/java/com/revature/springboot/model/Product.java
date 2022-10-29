package com.revature.springboot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity(name="products")
public @Data class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="product_name")
    private String name;

    @Column(name="brand")
    private String brand;

    @Column(name="price")
    private double price;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name="weight")
    private double weight;

    @Column(name="description")
    private String description;


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    private double avgRating = -1;

    @Column(name="stripe_key")
    private String stripeKey;


    @ManyToMany
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id"
            )
    )
    @JsonManagedReference
    private List<Category> categories;

}
