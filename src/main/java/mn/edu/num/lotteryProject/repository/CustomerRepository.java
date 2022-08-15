package mn.edu.num.lotteryProject.repository;

import mn.edu.num.lotteryProject.entity.Customer;
import mn.edu.num.lotteryProject.entity.CustomerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByRegistrationNumber(String registrationNumber);
    @Query(value = "SELECT id  FROM customer", nativeQuery = true)
    List<CustomerId> findAllById();
}
