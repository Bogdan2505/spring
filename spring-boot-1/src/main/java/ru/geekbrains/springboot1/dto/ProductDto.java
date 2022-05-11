package ru.geekbrains.springboot1.dto;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class ProductDto {

    private Long id;

    @NotBlank
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

    public ProductDto(String title) {
        this.title = title;
    }

    @Pattern(regexp = "^(?=.*?[0-9])(?=.*?[A-Z]).{8,}$", message = "Password too simple")
    @NotBlank
    private String password;

    public ProductDto(String title, int price, String password) {
        this.title = title;
        this.price = price;
        this.password = password;
    }

    public ProductDto(){};

    public ProductDto(Long id, @NotBlank String title, @Min(0) @Max(100000) int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

