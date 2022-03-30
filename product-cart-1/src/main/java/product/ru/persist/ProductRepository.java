package product.ru.persist;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();

    Product findById(long id);

    void init();

    void insert(Product user);

    void update(Product user);

    void delete(long id);

    long getCount();
}
