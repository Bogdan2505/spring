package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.model.Product;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

       EntityManager em = emFactory.createEntityManager();

        ProductDao productDao = new ProductDao(em);

        // productDao.filling();

        //System.out.println(productDao.findById(1L).toString());


        //Product product = new Product("cucumber", 100);

       //productDao.saveOrUpdate(product);

        productDao.findAll().forEach(System.out::println);

        productDao.end();
        emFactory.close();

    }
}
