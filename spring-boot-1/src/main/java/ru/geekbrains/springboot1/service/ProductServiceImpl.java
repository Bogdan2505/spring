package ru.geekbrains.springboot1.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.springboot1.controller.ProductSpecifications;
import ru.geekbrains.springboot1.dto.ProductDto;
import ru.geekbrains.springboot1.persist.Product;
import ru.geekbrains.springboot1.persist.ProductRepository;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<ProductDto> findProductByFilter(String titleFilter, Long idFilter, Integer page, Integer size, String sortField) {

        Specification<Product> spec = Specification.where(null);

        if(titleFilter != null){
            spec = spec.and(ProductSpecifications.titleContaining(titleFilter));
        }
        if(idFilter != null ){
            spec = spec.and(ProductSpecifications.idContaining(idFilter));
        }

            return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortField)))
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
