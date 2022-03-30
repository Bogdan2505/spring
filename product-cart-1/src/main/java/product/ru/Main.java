package product.ru;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import product.ru.persist.ProductRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ProductService productService = context.getBean("productService", ProductService.class);
        CartService cartService = context.getBean("cartService", CartService.class);

        cartService.addProductById(productService.findProductById(1L));
        cartService.showCart();

        cartService.addProductById(productService.findProductById(2L));
        cartService.showCart();

        CartService cartService2 = context.getBean("cartService", CartService.class);


        cartService2.addProductById(productService.findProductById(2L));
        cartService2.showCart();

     /*   for(;;) {
            System.out.println("if ypu want create cart write 1 \n " +
                    "if you want check product write 2 \n " +
                    "if you want add product in caart enter 3 ");
            Scanner sq = new Scanner(System.in);
        }*/



    }
}
