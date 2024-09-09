package ai.rnt.customerFeedback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("ai.rnt.customerFeedback.*")
@EnableJpaRepositories
@EnableCaching
public class CustomerFeedbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerFeedbackApplication.class, args);
	}

}
