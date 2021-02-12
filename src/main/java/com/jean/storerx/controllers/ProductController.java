package com.jean.storerx.controllers;

import com.jean.storerx.dtos.ProductDto;
import com.jean.storerx.dtos.ProductInputDto;
import com.jean.storerx.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNullApi;

@Component
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    public Mono<ServerResponse> create(final ServerRequest serverRequest) {
        final Mono<ProductInputDto> productInputDtoMono = serverRequest.bodyToMono(ProductInputDto.class);
        return productService.create(productInputDtoMono)
                .flatMap(productDto -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(productDto));
    }

    public Mono<ServerResponse> index(final ServerRequest serverRequest) {
        final int page = Integer.parseInt(serverRequest.queryParam("page").orElse("0"));
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.index(page), ProductDto.class);
    }

    public Mono<ServerResponse> update(final ServerRequest serverRequest) {
        Mono<ProductDto> productDtoMono = serverRequest.bodyToMono(ProductDto.class);

        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.update(productDtoMono), ProductDto.class);
    }

    public Mono<ServerResponse> get(ServerRequest serverRequest) {
        final Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        return ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(productService.get(id), ProductDto.class);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        final Integer id = Integer.valueOf(serverRequest.pathVariable("id"));
        return productService.delete(id)
                .flatMap(v -> ServerResponse.status(HttpStatus.OK).
                        contentType(MediaType.APPLICATION_JSON)
                        .build()
                );
    }
}
