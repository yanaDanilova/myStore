package de.danilova.myStore.core.services;

import de.danilova.myStore.core.repositories.CategoryRepository;
import de.danilova.myStore.core.entities.Category;
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
