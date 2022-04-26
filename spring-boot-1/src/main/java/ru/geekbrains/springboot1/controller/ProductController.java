package ru.geekbrains.springboot1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot1.dto.ProductDto;
import ru.geekbrains.springboot1.persist.Product;
import ru.geekbrains.springboot1.service.ProductService;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping("/product")
@Controller
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(@RequestParam Optional<String> titleFilter,
                           @RequestParam Optional<Long> idFilter,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size,
                           @RequestParam Optional<String> sortField,
                           Model model) {

        String titleFilterValue = titleFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        Long idFilterValue = idFilter
                //.filter(s -> !s)
                .orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");

        model.addAttribute("products", productService.
                findProductByFilter(titleFilterValue, idFilterValue, pageValue, sizeValue, sortFieldValue));

        return "product";

     /*   if ( titleFilter.isPresent() && !titleFilter.get().isBlank()) {
                model.addAttribute("products", productRepository.findByTitleContaining(titleFilter.get()));
            }else {
                    model.addAttribute("products", productRepository.findAllById(idFilter.get()));
          }*/
        // if(userNameFilter.isEmpty() || userNameFilter.isBlank()) {
       /* if(usernameFilter == null){
            model.addAttribute("products", productRepository.findAll());
        }else if (usernameFilter.equals("min")){
            model.addAttribute("products", productRepository.sortMin());
        }
        else if (usernameFilter.equals("max")){
            model.addAttribute("products", productRepository.sortMax());
        }*/

    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "product_form";
    }

    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("product", new ProductDto());
        return "product_form";
    }

    @PostMapping
    public String save(@Valid @ModelAttribute("product") ProductDto product, BindingResult binding) {
        if (binding.hasErrors()) {
            return "product_form";
        }
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        productService.deleteById(id);
        return "redirect:/product";
    }

}
