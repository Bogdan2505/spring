package ru.geekbrains.springboot1.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p order by p.price asc")
    List<Product> sortMin();

    @Query("select p from Product p order by p.price desc ")
    List<Product> sortMax();

    // List<Product> findProductByTitleLike();
}

/*
@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

   */
/* public ProductRepository(EntityManager entityManager){
        this.entityManager = entityManager;
    }*//*


*/
/*    @PostConstruct
    public void init() {
        this.save(new Product( "Product 1", 24));
        this.save(new Product( "Product 2"));
        this.save(new Product( "Product 3"));
        this.save(new Product( "Product 4"));
        this.save(new Product( "Product 5"));
    }*//*


    public List<Product> findAll() {

        return entityManager.createQuery("from Product p", Product.class).getResultList();
    }

    public Product findById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Transactional
    public void save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }

        //entityManager.getTransaction().begin();
        //entityManager.merge(product);
        //entityManager.getTransaction().commit();

    }

    @Transactional
    public void delete(long id) {

       */
/* Product product = entityManager.find(Product.class, id);
        entityManager.remove(product);*//*

        entityManager.createQuery("delete from Product where id = :id")
                .setParameter("id", id).executeUpdate();

    }

}
*/
