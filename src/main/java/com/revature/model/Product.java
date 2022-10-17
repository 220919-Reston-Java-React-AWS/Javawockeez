package com.revature.model;

import java.util.Objects;

public class Product {
    private int id;
    private String productName;
    private String brand;
    private double price;
    private String image_path;
    private double weight;
    private String description;

    public Product(){

    }

    public Product(String productName, String brand, String image_path){
        this.productName = productName;
        this.brand = brand;
        this.image_path = image_path;
    }

    public Product(String productName, String brand, double price, String image_path, double weight, String description){
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.image_path = image_path;
        this.weight = weight;
        this.description = description;
    }

    public Product(int id, String productName, String brand, String image_path){
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.image_path = image_path;
    }

    public Product(int id, String productName, String brand, double price, String image_path, double weight, String description){
        this.id = id;
        this.productName = productName;
        this.brand = brand;
        this.price = price;
        this.image_path = image_path;
        this.weight = weight;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getId() == product.getId() && Double.compare(product.getPrice(), getPrice()) == 0 && Double.compare(product.getWeight(), getWeight()) == 0 && Objects.equals(getProductName(), product.getProductName()) && Objects.equals(getBrand(), product.getBrand()) && Objects.equals(getImage_path(), product.getImage_path()) && Objects.equals(getDescription(), product.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductName(), getBrand(), getPrice(), getImage_path(), getWeight(), getDescription());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", image_path='" + image_path + '\'' +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                '}';
    }
}
