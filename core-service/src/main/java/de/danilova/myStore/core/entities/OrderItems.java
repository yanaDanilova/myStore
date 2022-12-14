package de.danilova.myStore.core.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name= "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private BigDecimal pricePerProduct;

    @Column(name = "price")
    private BigDecimal price;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;


    public void increment(){
        this.quantity++;
        this.price = pricePerProduct.multiply(new BigDecimal(quantity));
    }

    public void decrement(){
        this.quantity--;
        this.price = pricePerProduct.multiply(new BigDecimal(quantity));
    }

    public OrderItems(Product product, BigDecimal pricePerProduct, int quantity, BigDecimal price, Order order){
        this.product = product;
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price =product.getPrice();
        this.order = order;

    }

}
