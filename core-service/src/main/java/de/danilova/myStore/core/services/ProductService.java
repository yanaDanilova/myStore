package de.danilova.myStore.core.services;

import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.core.repositories.ProductRepository;
import de.danilova.myStore.core.entities.Product;
import de.danilova.myStore.core.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public Page<Product> getProducts(BigDecimal minPrice, BigDecimal maxPrice, String title, int pageIndex){
        Specification<Product> specification= Specification.where(null);
        if(minPrice != null){
            specification= specification.and(ProductSpecification.priceGreaterOrEqualsThen(minPrice));
        }
        if(maxPrice != null){
            specification = specification.and(ProductSpecification.priceLessOrEqualsThan(maxPrice));
        }
        if(title !=null){
            specification = specification.and(ProductSpecification.titleLike(title));
        }

       return productRepository.findAll(specification, PageRequest.of(pageIndex-1,5));
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    @Transactional
    public Product createNewProduct(ProductDto productDto){
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setCategory(categoryService.findCategoryByTitle(productDto.getCategoryTitle()).orElseThrow(()-> new ResourceNotFoundException("Category doesn't found")));
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
        return product;
    }
}

