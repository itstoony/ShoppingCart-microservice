package itstoony.com.github.shoppingchart.repository;

import itstoony.com.github.shoppingchart.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
