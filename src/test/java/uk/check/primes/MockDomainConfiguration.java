package uk.check.primes;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.mock;
import uk.check.primes.services.CheckPrimesService;

@Configuration
@EnableAutoConfiguration
public class MockDomainConfiguration {
	
	@Bean(name="service")
	public CheckPrimesService mockCheckPrimesService(){
		return mock(CheckPrimesService.class);
	}

}
