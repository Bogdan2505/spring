package ru.geekbrains.springboot1.persist;

import org.springframework.data.jpa.domain.Specification;

public final class ProductSpecifications {

    public static Specification<Product> titleContaining(String title ){
        return (root, query, cb) -> cb.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Product> idContaining(Long id){
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }

}
