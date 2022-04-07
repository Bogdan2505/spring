package ru.geekbrains.springboot1.persist;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class User {

    private Long id;

    @NotBlank
    private String username;


    @Min(0)
    @Max(100)
  //  @NotBlank
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public User(){};

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String printAge( int age){
        if ( age != 0){
            return String.valueOf(age);
        }
        return "возраст не указан";
    }

}
