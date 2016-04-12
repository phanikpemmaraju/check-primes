package uk.check.primes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="uk.check.primes")
public class CheckPrimesApplication{

	public static void main(String[] args) {
		SpringApplication.run(CheckPrimesApplication.class, args);
	}
	
}
