package mn.edu.num.lotteryProject.repository;

import mn.edu.num.lotteryProject.entity.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LotteryRepository extends JpaRepository<Lottery, Long>{
    Optional<Lottery> findByName(String name);
}
