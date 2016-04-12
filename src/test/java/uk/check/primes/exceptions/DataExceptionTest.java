package uk.check.primes.exceptions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DataExceptionTest {
	
	private DataException dataException;
	
	@Before
	public void setup(){
		dataException = new DataException();
	}
	
	
	@Test
	public void dataExceptionTest(){
		assertNotNull(dataException);
		assertNull(dataException.getCode());
		assertNull(dataException.getMessage());
		
		dataException = new DataException("error.invalid.number","Invalid Prime Range number entered");
		assertNotNull(dataException);		
		assertThat(dataException.getCode(), is("error.invalid.number"));
		assertThat(dataException.getMessage(), is("Invalid Prime Range number entered"));		
		
	}

}
