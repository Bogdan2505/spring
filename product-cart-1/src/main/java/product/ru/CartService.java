package product.ru;

import product.ru.persist.Product;
import product.ru.persist.ProductRepository;

import java.util.ArrayList;
import java.util.List;


public class CartService {

    private final ProductService productService;

    public CartService(ProductService productService) {
        this.productService = productService;
    }

    // private final ProductRepository productRepository;


    List<Product> cart = new ArrayList<>();

  /*  public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }*/

    public void addProductById(Product product){
        cart.add(product);
    }

    public void showCart(){
        System.out.println(cart.toString());
    }



}
