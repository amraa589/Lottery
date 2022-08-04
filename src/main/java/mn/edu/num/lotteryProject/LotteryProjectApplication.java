package mn.edu.num.lotteryProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan("mn.edu.num.lotteryProject.*")
public class LotteryProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(LotteryProjectApplication.class, args);
	}

}
