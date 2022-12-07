package de.danilova.myStore.core.controllers;

import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.api.ResourceNotFoundException;

import de.danilova.myStore.core.converters.ProductConverter;
import de.danilova.myStore.core.entities.Product;

import de.danilova.myStore.core.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(name="p", defaultValue = "1") int pageIndex,
            @RequestParam(name = "title_part",required = false) String titlePart,
            @RequestParam(name = "min_price",required = false) BigDecimal minPrice,
            @RequestParam(name = "max_price", required = false) BigDecimal maxPrice

    ) {
        if(pageIndex<1){
            pageIndex = 1;
        }
        Page<Product> productPage = productService.getProducts(minPrice,maxPrice,titlePart,pageIndex);
        return new PageImpl<>(productPage.getContent().stream().map(productConverter::entityToDto).collect(Collectors.toList()),productPage.getPageable(),productPage.getTotalElements());
   }

    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id).orElseThrow(()->new ResourceNotFoundException("Product with id: "+ id + " doesn't exist."));
        return productConverter.entityToDto(product);
    }


}
