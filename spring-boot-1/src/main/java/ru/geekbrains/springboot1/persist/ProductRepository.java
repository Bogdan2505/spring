package ru.geekbrains.springboot1.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final EntityManager entityManager;

    public ProductRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }

/*    @PostConstruct
    public void init() {
        this.save(new Product( "Product 1", 24));
        this.save(new Product( "Product 2"));
        this.save(new Product( "Product 3"));
        this.save(new Product( "Product 4"));
        this.save(new Product( "Product 5"));
    }*/

    public List<Product> findAll() {

        return entityManager.createQuery("from Product p", Product.class).getResultList();
    }

    public Product findById(long id) {
        return entityManager.find(Product.class, id);
    }

    public void save(Product product) {
       /* if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        productMap.put(product.getId(), product);
        return product;*/

        entityManager.getTransaction().begin();
        entityManager.merge(product);
        entityManager.getTransaction().commit();

    }

    public void delete(long id) {
        entityManager.getTransaction().begin();
        Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

}
