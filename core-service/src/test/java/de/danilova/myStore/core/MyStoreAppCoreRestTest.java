package de.danilova.myStore.core;

import de.danilova.myStore.api.ProductDto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class MyStoreAppCoreRestTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void productControllerTest(){
       List<ProductDto> productDtoList = testRestTemplate.getForObject("/api/v1/products", List.class);
        Assertions.assertEquals(10,productDtoList.size());
    }

}
