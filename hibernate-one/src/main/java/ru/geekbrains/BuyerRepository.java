package ru.geekbrains;

import ru.geekbrains.model.Buyer;
import ru.geekbrains.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.function.Consumer;

public class BuyerRepository {

    private final EntityManagerFactory emFactory;

    public BuyerRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public void save(Buyer buyer){
        executeInTransaction(em -> {
            if(buyer.getId() != null){
            em.persist(buyer);
        }else {
            em.merge(buyer);
        }
        em.getTransaction().commit();

    });
    }

    private void executeInTransaction(Consumer<EntityManager> consumer){

        EntityManager em = emFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }
    }
}
