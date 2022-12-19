package de.danilova.myStore.cart.models;
import de.danilova.myStore.api.ProductDto;
import lombok.Data;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Data

public class Cart {

    private List<CartItems> cartItemsList;
    private BigDecimal sum;

    public Cart(){
        this.cartItemsList = new ArrayList<>();
    }

    public List<CartItems> getCartItemsList(){
        return cartItemsList;
    }

    public void addProduct(ProductDto p){
        if(!cartItemsList.isEmpty()) {
            for (CartItems ci : cartItemsList) {
                if (ci.getProductId().equals(p.getId())) {
                    ci.incrementQuantity();
                    calculate();
                    return;
                }
            }
        }

            CartItems cartItems = new CartItems(p.getId(), p.getTitle(), 1, p.getPrice(), p.getPrice());
            cartItemsList.add(cartItems);
            calculate();
        }



    public void calculate(){
        sum = BigDecimal.ZERO;
        for(CartItems ci: cartItemsList){
            sum = sum.add(ci.getPrice());
        }
    }

    public  void clear(){
        cartItemsList.clear();
        sum=BigDecimal.ZERO;
    }



    public void removeProduct(Long productId) {//todo
        if(cartItemsList.removeIf(cartItems -> cartItems.getProductId().equals(productId))){
            calculate();
        }
    }
}
