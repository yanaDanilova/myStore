package de.danilova.myStore.controllers;

import de.danilova.myStore.entities.Category;
import de.danilova.myStore.exceptions.ResourceNotFoundException;
import de.danilova.myStore.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
