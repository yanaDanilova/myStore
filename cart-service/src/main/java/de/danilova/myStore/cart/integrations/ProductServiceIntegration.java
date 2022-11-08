package de.danilova.myStore.cart.integrations;

import de.danilova.myStore.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final RestTemplate restTemplate;


    public Optional<ProductDto> getProductById(Long productId){
       return Optional.ofNullable(restTemplate.getForObject("http://localhost:8090/store/api/v1/products/"+ productId, ProductDto.class));
    }


}
