package de.danilova.myStore.core.integrations;

import de.danilova.myStore.api.CartDto;
import de.danilova.myStore.cart.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private  final RestTemplate restTemplate;

    public CartDto getCurrentCartDto(){
       return restTemplate.getForObject("http://localhost:8190/store-carts/api/v1/carts", CartDto.class);
    }
}
