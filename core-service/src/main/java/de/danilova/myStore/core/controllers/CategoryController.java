package de.danilova.myStore.core.controllers;

import de.danilova.myStore.api.CategoryDto;
import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.core.converters.CategoryConverter;
import de.danilova.myStore.core.entities.Category;
import de.danilova.myStore.core.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;

    @Operation(
            summary = "Request to get category by title",
            responses = {
                    @ApiResponse(
                            description = "Successful response", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Category.class))

                    )
            }
    )
    @GetMapping
    public CategoryDto findCategoryByTitle(@RequestParam String title){
       Category category = categoryService.findCategoryByTitle(title).orElseThrow(()->new ResourceNotFoundException("Category with title "+ title+ " doesn't exist"));
       return categoryConverter.entityToDto(category);
    }

}
