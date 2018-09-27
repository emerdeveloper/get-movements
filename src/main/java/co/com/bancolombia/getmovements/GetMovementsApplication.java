package co.com.bancolombia.getmovements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.com.bancolombia" })
public class GetMovementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetMovementsApplication.class, args);
	}
}
