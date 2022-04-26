package ru.geekbrains.springboot1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboot1.dto.ProductDto;
import ru.geekbrains.springboot1.persist.Product;
import ru.geekbrains.springboot1.persist.ProductRepository;
import ru.geekbrains.springboot1.persist.ProductSpecifications;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findProductByFilter(String titleFilter, Long idFilter, Integer page, Integer size) {

        Specification<Product> spec = Specification.where(null);

        if(titleFilter != null){
            spec = spec.and(ProductSpecifications.titleContaining(titleFilter));
        }
        if(idFilter != null ){
            spec = spec.and(ProductSpecifications.idContaining(idFilter));
        }

        return productRepository.findAll(spec, PageRequest .of(page, size, Sort.by("id")))
                .map(ProductServiceImpl::productToDto);
    }

    @Override
    public Optional<ProductDto> findById(Long id) {
        return productRepository.findById(id).map(
                ProductServiceImpl::productToDto);
    }

    @Override
    public ProductDto save(ProductDto product) {
        return productToDto(productRepository.save(new Product(product.getId(), product.getTitle(), product.getPrice())));
    }

    @Override
    public void deleteById(long id) {
        productRepository.deleteById(id);
    }

    private static ProductDto productToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }
}
