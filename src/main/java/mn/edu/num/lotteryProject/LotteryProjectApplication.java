package mn.edu.num.lotteryProject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan("mn.edu.num.lotteryProject.*")
public class LotteryProjectApplication extends SpringBootServletInitializer {

	private static final Logger logger = LoggerFactory.getLogger(LotteryProjectApplication.class);
	public static void main(String[] args) {
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");

		SpringApplication.run(LotteryProjectApplication.class, args);
	}

}
