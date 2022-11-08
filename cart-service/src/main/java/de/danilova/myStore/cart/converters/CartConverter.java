package de.danilova.myStore.cart.converters;

import de.danilova.myStore.api.CartDto;
import de.danilova.myStore.cart.models.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {
    private final CartItemsConverter cartItemsConverter;

    public CartDto modelToDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setCartItemsList(cart.getCartItemsList().stream().map(cartItemsConverter::modelToDto).collect(Collectors.toList()));
        cartDto.setSum(cart.getSum());
        return cartDto;
    }
}
