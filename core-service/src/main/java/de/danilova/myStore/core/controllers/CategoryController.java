package de.danilova.myStore.core.controllers;

import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.core.entities.Category;
import de.danilova.myStore.core.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor

public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public Category findCategoryByTitle(@RequestParam String title){
       return categoryService.findCategoryByTitle(title).orElseThrow(()->new ResourceNotFoundException("Category with title "+ title+ " doesn't exist"));
    }
}
