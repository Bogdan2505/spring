package product.ru;

import product.ru.persist.Product;
import product.ru.persist.ProductRepository;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void insert (Product product ){
        productRepository.insert(product);
    }

    public Product findProductById (Long id){
        return productRepository.findById(id);
    }
}
