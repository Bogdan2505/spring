package ru.geekbrains.springboot1.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.springboot1.controller.NotFoundException;
import ru.geekbrains.springboot1.dto.ProductDto;
import ru.geekbrains.springboot1.service.ProductService;

import java.sql.SQLException;
import java.util.Optional;

@RequestMapping("/rest/v1/product")
@RestController
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/all")
    private Page<ProductDto> findAll(@RequestParam Optional<String> titleFilter,
                                     @RequestParam Optional<Long> idFilter,
                                     @RequestParam Optional<Integer> page,
                                     @RequestParam Optional<Integer> size,
                                     @RequestParam Optional<String> sortField) {


        String titleFilterValue = titleFilter
                .filter(s -> !s.isBlank())
                .orElse(null);
        Long idFilterValue = idFilter
                //.filter(s -> !s)
                .orElse(null);
        Integer pageValue = page.orElse(1) - 1;
        Integer sizeValue = size.orElse(3);
        String sortFieldValue = sortField.filter(s -> !s.isBlank()).orElse("id");


        return productService.findProductByFilter(titleFilterValue, idFilterValue, pageValue, sizeValue, sortFieldValue);
    }

    @GetMapping("/{id}/id")
    public ProductDto findById(@PathVariable Long id){
        return productService.findById(id).orElseThrow(()-> new NotFoundException("Product not found"));
    }

    @PostMapping
    public ProductDto create(@RequestBody ProductDto productDto){
        if(productDto.getId() != null){
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        return productService.save(productDto);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto){
        if(productDto.getId() == null){
            throw new IllegalArgumentException("Created product shouldn't have id");
        }
        return productService.save(productDto);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public String notFoundException(NotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String illegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public String sqlException(SQLException ex) {
        return ex.getMessage();
    }
}
