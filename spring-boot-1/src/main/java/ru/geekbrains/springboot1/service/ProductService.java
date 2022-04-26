package ru.geekbrains.springboot1.service;

import org.springframework.data.domain.Page;
import ru.geekbrains.springboot1.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Page<ProductDto> findProductByFilter(String titleFilter, Long idFilter, Integer page, Integer size, String sortField);

    Optional<ProductDto> findById(Long id);

    ProductDto save(ProductDto product);

    void deleteById(long id);

}
