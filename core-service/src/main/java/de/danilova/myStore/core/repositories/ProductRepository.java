package de.danilova.myStore.core.repositories;

import de.danilova.myStore.core.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Product,Long> {

}
