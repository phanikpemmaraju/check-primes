package uk.check.primes.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class ErrorMessageTest {
	
	@Test
    public void shouldReturnErrorBuilder() {
		assertThat(new ErrorMessage.ErrorBuilder(null, null, null),isA(ErrorMessage.ErrorBuilder.class));
    }
	
	@Test
    public void shouldBuildADefaultError() {
        assertThat(new ErrorMessage.ErrorBuilder(null, null, null).build(), isA(ErrorMessage.class));
    }
	
	@Test
    public void shouldBuildAnErrorMessage() {
		ErrorMessage error = new ErrorMessage.ErrorBuilder("400","Invalid Number entered","Bad Request")
                .build();

        assertThat(error.getCode(), is("400"));
        assertThat(error.getMessage(), is("Invalid Number entered"));
        assertThat(error.getDescription(), is("Bad Request"));
                
        final String value = "ErrorMessage [code=400, message=Invalid Number entered, description=Bad Request]";
        assertThat(error.toString(), is(value));
    }

}
