package de.danilova.myStore.core;
import de.danilova.myStore.api.CartDto;
import de.danilova.myStore.api.CartItemDto;
import de.danilova.myStore.core.entities.Category;
import de.danilova.myStore.core.entities.Order;
import de.danilova.myStore.core.entities.Product;
import de.danilova.myStore.core.integrations.CartServiceIntegration;
import de.danilova.myStore.core.repositories.OrderRepository;
import de.danilova.myStore.core.services.OrderService;
import de.danilova.myStore.core.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class MyStoreCoreAppOrderServiceMockTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private   CartServiceIntegration cartServiceIntegration;
    @MockBean
    private  ProductService productService;
    @MockBean
    private OrderRepository orderRepository;


    @Test
    public void createOrder(){

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductTitle("T-Shirt");
        cartItemDto.setProductId(123L);
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(59.99));
        cartItemDto.setQuantity(1);
        cartItemDto.setPrice(BigDecimal.valueOf(59.99));

        List<CartItemDto> cartItemDtoList = new ArrayList<>();
        cartItemDtoList.add(cartItemDto);

        CartDto cartDto = new CartDto();
        cartDto.setCartItemsList(cartItemDtoList);
        cartDto.setSum(BigDecimal.valueOf(59.99));

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCartDto(null);

        Category category = new Category();
        category.setId(234L);
        category.setTitle("Clothes");

        Product product = new Product();
        product.setCategory(category);
        product.setId(123L);
        product.setTitle("T-Shirt");
        product.setPrice(BigDecimal.valueOf(59.99));


        Mockito.doReturn(Optional.of(product)).when(productService).getProductById(123L);
        Order order = orderService.createOrder("Bob");

        Assertions.assertEquals(BigDecimal.valueOf(59.99),order.getTotalPrice());

        Mockito.verify(orderRepository,Mockito.times(1)).save(ArgumentMatchers.any());

    }


}
