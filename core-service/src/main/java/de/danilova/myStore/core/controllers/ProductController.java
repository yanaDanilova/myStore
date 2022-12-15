package de.danilova.myStore.core.controllers;

import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.api.ResourceNotFoundException;

import de.danilova.myStore.api.StoreError;
import de.danilova.myStore.core.MyStoreCoreApp;
import de.danilova.myStore.core.converters.ProductConverter;
import de.danilova.myStore.core.entities.Product;

import de.danilova.myStore.core.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Products", description = "Methods of working with products")
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;


    @Operation(
            summary = "Request to get a filtered list of products",
            responses = {
                    @ApiResponse(
                           description = "Successful responce", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
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

    @Operation(
            summary = "Request to receive a product by id",
            responses = {
                    @ApiResponse(
                            description = "The Product was successfully found and returned", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "The Product wasn't found", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = StoreError.class))
                    )

            }
    )
    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable @Parameter(description = "product identifier", required = true) Long id){
        Product product = productService.getProductById(id).orElseThrow(()->new ResourceNotFoundException("Product with id: "+ id + " doesn't exist."));
        return productConverter.entityToDto(product);
    }


}
