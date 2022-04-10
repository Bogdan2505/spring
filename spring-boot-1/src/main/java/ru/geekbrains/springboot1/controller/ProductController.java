package ru.geekbrains.springboot1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.springboot1.persist.Product;
import ru.geekbrains.springboot1.persist.ProductRepository;

import javax.validation.Valid;

@RequestMapping("/product")
@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid Product product, BindingResult binding) {
        if( binding.hasErrors()){
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete( @PathVariable("id") long id) {
        productRepository.delete(id);
        return "redirect:/product";
    }
}
