package de.danilova.myStore.cart.integrations;

import de.danilova.myStore.api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;



@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {
    private final WebClient webClient;



    public ProductDto getProductById(Long productId){
       return webClient.get().uri("api/v1/products/" + productId).retrieve().bodyToMono(ProductDto.class).block();
    }


}
