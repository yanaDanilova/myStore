package de.danilova.myStore.cart.converters;

import de.danilova.myStore.api.CartItemDto;
import de.danilova.myStore.cart.models.CartItems;
import org.springframework.stereotype.Component;

@Component
public class CartItemsConverter {

    public CartItemDto modelToDto(CartItems cartItems){
         CartItemDto cartItemDto = new CartItemDto();
         cartItemDto.setProductTitle(cartItems.getProductTitle());
         cartItemDto.setProductId(cartItems.getProductId());
         cartItemDto.setPricePerProduct(cartItems.getPricePerProduct());
         cartItemDto.setQuantity(cartItems.getQuantity());
         cartItemDto.setPrice(cartItems.getPrice());
         return cartItemDto;
    }


}