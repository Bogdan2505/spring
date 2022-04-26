package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Buyer;
import ru.geekbrains.model.LineItem;
import ru.geekbrains.model.Product;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        // Buyer buyer = em.find(Buyer.class, 1L);

       em.getTransaction().begin();
        em.persist(new Product("lime", 300));
        em.getTransaction().commit();
        em.close();

       /* em.getTransaction().begin();
        Buyer buyer = new Buyer("Troy");
        buyer.getProductList().add(new Product("tomat", 50, buyer));
        em.persist(buyer);
        em.getTransaction().commit();
        em.close();*/

        Buyer buyer = em.find(Buyer.class, 1L);
        List<LineItem> productList = buyer.getLineItems();
       //System.out.println(productList.get(0).toString());



        //ProductRepository productRepository = new ProductRepository(emFactory);
        /// BuyerRepository buyerRepository = new BuyerRepository(emFactory);

        //productRepository.save(new Product("cucumber3", 102));
        //buyerRepository.save(new Buyer("Genre"));


        // productDao.filling();

        //System.out.println(productDao.findById(1L).toString());


        //Product product = new Product("cucumber", 100);

        //productDao.saveOrUpdate(product);

        // productDao.findAll();


    }
}
