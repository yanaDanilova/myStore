package de.danilova.myStore.core;

import de.danilova.myStore.core.entities.Category;

import de.danilova.myStore.core.repositories.CategoryRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class MyStoreAppCoreRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestEntityManager  testEntityManager;

    @Test
    public void repositoryTest(){
      Category category = new Category();
      category.setTitle("Food");

      testEntityManager.persist(category);
      testEntityManager.flush();

        List<Category> categories = categoryRepository.findAll();
        Assertions.assertEquals(3,categories.size());
        Assertions.assertEquals("Food", categories.get(2).getTitle());
    }
}
