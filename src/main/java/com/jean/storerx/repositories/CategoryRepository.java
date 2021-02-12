package com.jean.storerx.repositories;

import com.jean.storerx.models.Category;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CategoryRepository extends ReactiveCrudRepository<Category, Integer> {
}
