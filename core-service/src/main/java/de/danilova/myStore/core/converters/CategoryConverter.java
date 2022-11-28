package de.danilova.myStore.core.converters;

import de.danilova.myStore.api.CategoryDto;
import de.danilova.myStore.core.entities.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

   private final ProductConverter productConverter;

    public CategoryDto entityToDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setTitle(category.getTitle());
        categoryDto.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return categoryDto;
    }

    public Category dtoToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setTitle(categoryDto.getTitle());
        category.setProducts(categoryDto.getProducts().stream().map(productConverter::dtoToEntity).collect(Collectors.toList()));
        return category;
    }
}
