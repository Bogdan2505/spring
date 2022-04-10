package ru.geekbrains.springboot1.persist;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String title;

    @Min(0)
    @Max(100000)
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Product(String title) {
        this.title = title;
    }

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public Product(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String printPrice( int price){
        if ( price != 0){
            return String.valueOf(price);
        }
        return "цена не указана";
    }

}
