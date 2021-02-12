package com.jean.storerx.mappers;

import com.jean.storerx.dtos.ProductDto;
import com.jean.storerx.dtos.ProductInputDto;
import com.jean.storerx.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {Qualifiers.class})
public abstract class ProductMapper {

    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "description", target = "description")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    public abstract Product mapFrom(final ProductInputDto productInputDto);

    @Mapping(source = "description", target = "description")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "category.id", target = "category.id")
    @Mapping(source = "category.name", target = "category.name")
    @Mapping(source = "createdAt", target = "createdAt", qualifiedByName = {"Qualifiers", "toStringDate"})
    @Mapping(source = "updatedAt", target = "updatedAt", qualifiedByName = {"Qualifiers", "toStringDate"})
    @Mapping(source = "id", target = "id")
    public abstract ProductDto mapFrom(final Product product);
}
