package mn.edu.num.lotteryProject.repository;

import mn.edu.num.lotteryProject.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
