package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductDao {

    private EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    public void filling() {
        em.getTransaction().begin();
        em.persist(new Product("bred1", 70));
        em.persist(new Product("milk1", 80));
        em.persist(new Product("meat1", 400));
        em.getTransaction().commit();
    }

    public void end() {
        em.close();
    }

    public Product findById(Long id) {
        return em.find(Product.class, 1L);
    }

    public void saveOrUpdate(Product product){


          /*  Product productInDB = em.find(product.getClass(), product.getId());
            productInDB.setTitle(product.getTitle());
            productInDB.setPrice(product.getPrice());*/
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();

    }

    public List<Product> findAll(){
        em.getTransaction();
        List<Product> products =  em.createQuery("from Product p", Product.class).getResultList();
       // em.getTransaction().commit();
        return products;
    }

}
