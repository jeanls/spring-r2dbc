package com.jean.storerx.repositories;

import com.jean.storerx.models.Product;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveCrudRepository<Product, Integer> {

    @Query("select * from products left outer join categories on categories.id = products.category_id where products.id = :id")
    Mono<Product> findByIdJoin(final Integer id);
}
