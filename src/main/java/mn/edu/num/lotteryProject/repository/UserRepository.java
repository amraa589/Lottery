package mn.edu.num.lotteryProject.repository;

import mn.edu.num.lotteryProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

}
