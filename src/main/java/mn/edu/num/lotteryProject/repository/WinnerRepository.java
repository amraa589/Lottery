package mn.edu.num.lotteryProject.repository;

import mn.edu.num.lotteryProject.entity.User;
import mn.edu.num.lotteryProject.entity.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WinnerRepository extends JpaRepository<Winner,Long> {
    Optional<Winner> findByRegistrationNumber(String registrationNumber);

    long countWinnersByLotteryId(long lotteryId);
}
