package com.jean.storerx.routers;

import com.jean.storerx.controllers.ProductController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class ProductRouter {

    private final String BASE_PATH = "/products";

    @Bean
    public RouterFunction<ServerResponse> createRoute(ProductController productController) {
        return RouterFunctions
                .route(POST(BASE_PATH).and(accept(MediaType.APPLICATION_JSON)), productController::create)
                .andRoute(GET(BASE_PATH).and(accept(MediaType.APPLICATION_JSON)), productController::index)
                .andRoute(GET(BASE_PATH + "/{id}").and(accept(MediaType.APPLICATION_JSON)), productController::get)
                .andRoute(DELETE(BASE_PATH + "/{id}").and(accept(MediaType.APPLICATION_JSON)), productController::delete)
                .andRoute(PUT(BASE_PATH).and(accept(MediaType.APPLICATION_JSON)), productController::update);

    }

}
