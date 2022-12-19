package de.danilova.myStore.core.integrations;

import de.danilova.myStore.api.CartDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private  final WebClient cartServiceWebClient;

    public CartDto getCurrentCartDto(String username){
       return cartServiceWebClient.get().uri("/api/v1/carts/0").header("username", username).retrieve().bodyToMono(CartDto.class).block();
    }

    public void clear(String username){
        cartServiceWebClient.get().uri("/api/v1/carts/0/clear").header("username", username).retrieve().toBodilessEntity().block();
    }
}
