package de.danilova.myStore.core.services;

import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.core.repositories.ProductRepository;
import de.danilova.myStore.core.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    public List<Product> getAllProducts(){
       return productRepository.findAll();
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

