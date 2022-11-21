package de.danilova.myStore.core.integrations;

import de.danilova.myStore.api.CartDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private  final WebClient cartServiceWebClient;

    public CartDto getCurrentCartDto(){
       return cartServiceWebClient.get().uri("/api/v1/carts").retrieve().bodyToMono(CartDto.class).block();
    }

    public void clear(){
        cartServiceWebClient.get().uri("/api/v1/carts/clear").retrieve().toBodilessEntity().block();
    }
}
