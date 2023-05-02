package itstoony.com.github.shoppingchart.repository;

import itstoony.com.github.shoppingchart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean quantityAvailable(Long productID, Integer quantity);

}
