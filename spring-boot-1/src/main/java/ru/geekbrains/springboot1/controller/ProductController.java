package ru.geekbrains.springboot1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String listPage(@RequestParam( required = false) String usernameFilter, Model model) {
        System.out.println( " filter " + usernameFilter);

       // if(userNameFilter.isEmpty() || userNameFilter.isBlank()) {
        if(usernameFilter == null){
            model.addAttribute("products", productRepository.findAll());
        }else if (usernameFilter.equals("min")){
            model.addAttribute("products", productRepository.sortMin());
        }
        else if (usernameFilter.equals("max")){
            model.addAttribute("products", productRepository.sortMax());
        }
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
        productRepository.deleteById(id);
        return "redirect:/product";
    }
}
