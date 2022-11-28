package de.danilova.myStore.core.controllers;

import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.api.ResourceNotFoundException;

import de.danilova.myStore.core.converters.ProductConverter;
import de.danilova.myStore.core.entities.Product;

import de.danilova.myStore.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        List <Product> productList = productService.getAllProducts();
        return productList.stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id).orElseThrow(()->new ResourceNotFoundException("Product with id: "+ id + " doesn't exist."));
        return productConverter.entityToDto(product);
    }


}
