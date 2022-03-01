package ru.geekbrains;

import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/product_info/*")
public class ProductInfo extends HttpServlet {

    private ProductRepository productRepository;


    @Override
    public void init() throws ServletException {
        productRepository = new ProductRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String str = req.getPathInfo().substring(1);
        System.out.println(str);
        resp.getWriter().println("id: " + str + " name for this id:" +
                productRepository.findById(Long.parseLong(str)).getTitle()
        );
    }
}