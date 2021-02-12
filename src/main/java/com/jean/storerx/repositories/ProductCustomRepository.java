package com.jean.storerx.repositories;

import com.jean.storerx.models.Product;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@AllArgsConstructor
public class ProductCustomRepository {

    private final R2dbcEntityTemplate entityTemplate;

    public Flux<Product> findAllPagAndSort(int page) {
        int offset = (page - 1) * 10;

        return entityTemplate.select(Product.class)
                .matching(Query.query(CriteriaDefinition.empty())
                        .sort(Sort.by("id").descending())
                        .limit(10)
                        .offset(offset))
                .all();

    }
}
