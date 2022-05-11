package ru.geekbrains.springboot1.persist;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p order by p.price asc")
    List<Product> sortMin();

    @Query("select p from Product p order by p.price desc ")
    List<Product> sortMax();

    List<Product> findByTitleContaining(@Param("productTitle") String title);

    List<Product> findAllById(@Param("productId") Long id);

    @Query("select p from Product p " +
            " where (p.title like concat('%', :title, '%') or :title is null) and" +
            "       (p.id = :id or :id is null) ")
    List<Product> findProductByFilter(@Param("id") Long id,
                                      @Param("title") String title);

    Optional<Product> findProductByTitle(@Param("title") String title);

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
