package de.danilova.myStore.core.repositories;

import de.danilova.myStore.core.entities.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Long> {
}
