package de.danilova.myStore.core.converters;


import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.core.entities.Product;
import de.danilova.myStore.core.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;

    public ProductDto entityToDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setTitle(product.getTitle());
        productDto.setCategoryTitle(product.getCategory().getTitle());
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        return productDto;
    }

    public Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setId(productDto.getId());
        product.setCategory(categoryService.findCategoryByTitle(productDto.getCategoryTitle()).orElseThrow(()-> new ResourceNotFoundException("Category doesn't found")));
        return product;
    }

}
