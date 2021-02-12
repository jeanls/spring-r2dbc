package com.jean.storerx.services;

import com.jean.storerx.dtos.ProductDto;
import com.jean.storerx.dtos.ProductInputDto;
import com.jean.storerx.exceptions.NotFoundException;
import com.jean.storerx.mappers.ProductMapper;
import com.jean.storerx.models.Product;
import com.jean.storerx.repositories.CategoryRepository;
import com.jean.storerx.repositories.ProductCustomRepository;
import com.jean.storerx.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductCustomRepository productCustomRepository;

    public Mono<ProductDto> create(final Mono<ProductInputDto> productInputDtoMono) {
        Mono<Product> productMono = productInputDtoMono
                .map(productInputDto -> {
                    final Product product = ProductMapper.INSTANCE.mapFrom(productInputDto);
                    product.setCreatedAt(LocalDateTime.now());
                    product.setUpdatedAt(product.getCreatedAt());
                    return product;
                }).flatMap(productRepository::save);

        return productMono.map(ProductMapper.INSTANCE::mapFrom);
    }

    public Mono<ProductDto> update(final Mono<ProductDto> productDtoMono) {
        return productDtoMono
                .flatMap(productDto -> productRepository.findById(productDto.getId())
                        .switchIfEmpty(Mono.error(new NotFoundException("Product not found")))
                        .flatMap(product -> {
                            product.setName(productDto.getName());
                            product.setDescription(productDto.getDescription());
                            product.setUpdatedAt(LocalDateTime.now());
                            return productRepository.save(product);
                        }).map(ProductMapper.INSTANCE::mapFrom));
    }

    public Flux<ProductDto> index(final int page) {
        Flux<Product> productFlux = productCustomRepository.findAllPagAndSort(page);
        return productFlux.map(ProductMapper.INSTANCE::mapFrom);
    }

    public Mono<ProductDto> get(final Integer id) {

        Mono<ProductDto> productDtoMono = productRepository.findById(id)
                .doOnNext(product -> {
                    
                })
                .map(ProductMapper.INSTANCE::mapFrom)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found")));



        return productDtoMono;
    }

    public Mono<Void> delete(final Integer id) {
        return productRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Product not found")))
                .flatMap(productRepository::delete);
    }
}
