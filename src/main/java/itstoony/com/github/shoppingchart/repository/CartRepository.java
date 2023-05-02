package itstoony.com.github.shoppingchart.repository;

import itstoony.com.github.shoppingchart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
