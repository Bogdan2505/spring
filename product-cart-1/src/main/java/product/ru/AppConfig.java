package product.ru;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import product.ru.persist.ProductRepository;
import product.ru.persist.ProductRepositoryImpl;

@Configuration
@ComponentScan("product.ru")
public class AppConfig {

    @Bean
    public ProductRepository productRepository(){
        return new ProductRepositoryImpl();
    }

    @Bean
    public ProductService productService(ProductRepository productRepository){
        return new ProductService(productRepository);
    }

    @Bean
    public ProductRepository init(){
        productRepository().init();
        return productRepository();
    }

    @Bean
    @Scope("prototype")
    public CartService cartService(ProductRepository productRepository){
        return new CartService(productRepository);
    }

}
