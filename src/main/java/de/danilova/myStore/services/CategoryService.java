package de.danilova.myStore.services;

import de.danilova.myStore.entities.Category;
import de.danilova.myStore.exceptions.ResourceNotFoundException;
import de.danilova.myStore.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;


    public Optional<Category> findCategoryById(Long Id){
        return categoryRepository.findById(Id);
    }

    public Optional<Category> findCategoryByTitle(String title){
        return categoryRepository.findCategoryByTitle(title);
    }

}
