package ru.geekbrains;

import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class ProductRepository {


    private final EntityManagerFactory emFactory;


    public ProductRepository(EntityManagerFactory emFactary) {
        this.emFactory = emFactary;
    }

 /*   public void filling() {
        em.getTransaction().begin();
        em.persist(new Product("bred1", 70));
        em.persist(new Product("milk1", 80));
        em.persist(new Product("meat1", 400));
        em.getTransaction().commit();
    }*/

    public Optional<Product> findById(Long id) {
        return executeForEntityManadger(
                em -> Optional.ofNullable(em.find(Product.class, id)));

    }

    /*  public void saveOrUpdate(Product product){


     *//*  Product productInDB = em.find(product.getClass(), product.getId());
            productInDB.setTitle(product.getTitle());
            productInDB.setPrice(product.getPrice());*//*
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();

    }*/

    public void save(Product product){
        executeInTransaction(em -> {
            if(product.getId() != null){
                em.persist(product);
            }else {
                em.merge(product);
            }
            em.getTransaction().commit();

        });

    }

  /*  public void save(Product product) {
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
    }*/


    public Optional<List<Product>> findAll() {

        //List<Product> products =  em.createQuery("from Product p", Product.class).getResultList();
        return executeForEntityManadger(em -> Optional.ofNullable(em.createNamedQuery("findAllProducts", Product.class)
                .getResultList()));
    }

    private <R> R executeForEntityManadger(Function<EntityManager, R> func) {

        EntityManager em = emFactory.createEntityManager();
        try {
            return func.apply(em);
        } finally {
            em.close();
        }

    }

    private void executeInTransaction(Consumer<EntityManager> consumer) {

        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

}
