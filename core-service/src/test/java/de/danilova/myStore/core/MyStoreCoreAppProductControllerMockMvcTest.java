package de.danilova.myStore.core;
import de.danilova.myStore.api.ProductDto;
import de.danilova.myStore.core.converters.ProductConverter;
import de.danilova.myStore.core.entities.Category;
import de.danilova.myStore.core.entities.Product;
import de.danilova.myStore.core.services.ProductService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
@AutoConfigureMockMvc
public class MyStoreCoreAppProductControllerMockMvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductConverter productConverter;

    @Test
    public void getProductById() throws Exception {
        Category category = new Category();
        category.setId(3244L);
        category.setTitle("Clothes");

        Product product = new Product();
        product.setId(123L);
        product.setTitle("T-Shirt");
        product.setPrice(BigDecimal.valueOf(59.99));
        product.setCategory(category);

        given(productService.getProductById(123L)).willReturn(Optional.of(product));

        ProductDto productDto = new ProductDto();
        productDto.setCategoryTitle(product.getCategory().getTitle());
        productDto.setId(product.getId());
        productDto.setPrice(product.getPrice());
        productDto.setTitle(product.getTitle());

        given(productConverter.entityToDto(product)).willReturn(productDto);
        mvc.perform(get("/api/v1/products/123")).andDo(print()).andExpect((jsonPath("$.title")).value("T-Shirt"));




    }






}
