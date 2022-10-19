package de.danilova.myStore.utils;

import de.danilova.myStore.entities.Product;
import de.danilova.myStore.services.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Component
@Data
@RequiredArgsConstructor
public class Cart {

    private final ProductService productService;
    private List<Product> cartList;
    private BigDecimal sum;

    @PostConstruct
    public void init(){
        this.cartList = new ArrayList<>();

    }

    public void addProduct(Long id){
        Product product = productService.getProductById(id).get();
        cartList.add(product);
        calculate();
    }

    public void calculate(){
        sum = BigDecimal.ZERO;
        for(Product p: cartList){
            sum= sum.add(p.getPrice());
        }
    }

    public void deleteProductFromCart(Long id){
       cartList.removeIf(product -> product.getId().equals(id));
       calculate();
    }

    public void clearCart(){
        cartList.clear();
        calculate();
    }

}
