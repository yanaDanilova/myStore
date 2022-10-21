package de.danilova.myStore.controllers;

import de.danilova.myStore.dtos.ProductDto;
import de.danilova.myStore.entities.Product;
import de.danilova.myStore.exceptions.ResourceNotFoundException;
import de.danilova.myStore.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(){
        List <Product> productList = productService.getAllProducts();
        return productList.stream().map(ProductDto::new).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id).orElseThrow(()->new ResourceNotFoundException("Product with id: "+ id + " doesn't exist."));
        return new ProductDto(product);
    }


}
