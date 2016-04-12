package uk.check.primes.services;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import  uk.check.primes.services.CheckPrimesService;
import uk.check.primes.exceptions.DataException;

public class CheckPrimesServiceTest {
	
	private CheckPrimesService service;
	
	@Before
	public void setUp(){
		service = new CheckPrimesService();
	}
		
	@Test
	public void testIsPrime(){
		assertFalse(service.isPrime(-1));
		assertTrue(service.isPrime(2));
	}
			
	@Test
	public void testGeneratePrimesUsingTrialDivision() throws Exception{
		List<Integer> list = service.generatePrimesUsingTrialDivision(6, 8);
		assertThat(list, hasSize(1));
		assertEquals(list.get(0).intValue(),7);
		
		list = service.generatePrimesUsingTrialDivision(14, 16);
		assertThat(list, hasSize(0));
	}
	
	@Test(expected=DataException.class)
	public void testGeneratePrimesTrailAlgoSingleThreadException(){
		service.generatePrimesTrailAlgoSingleThread(-1);
	}
	
	@Test
	public void testGeneratePrimesTrailAlgoSingleThread(){
		List<Integer> list = service.generatePrimesTrailAlgoSingleThread(10);
		assertThat(list, hasSize(4));
		assertEquals(list.get(0).intValue(),2);
		assertEquals(list.get(1).intValue(),3);
		assertEquals(list.get(2).intValue(),5);
		assertEquals(list.get(3).intValue(),7);
		
		list = service.generatePrimesTrailAlgoSingleThread(101);
		assertThat(list, hasSize(26));
	}

	
	@Test(expected=DataException.class)
	public void testGeneratePrimesTrailAlgoMultiThreadException(){
		service.generatePrimesTrailAlgoMultiThread(-1);
	}
	
	@Test
	public void testGeneratePrimesTrailAlgoMultiThread(){
		service.setThreads(5);
		List<Integer> list = service.generatePrimesTrailAlgoMultiThread(101);
		assertThat(list, hasSize(26));
		assertEquals(list.get(4).intValue(),11);
		assertEquals(list.get(5).intValue(),13);
		assertEquals(list.get(6).intValue(),17);
		assertEquals(list.get(7).intValue(),19);
	}
	
	@Test(expected=DataException.class)
	public void testGeneratePrimesUsingSieveofEratosthenessException(){
		service.generatePrimesUsingSieveofEratostheness(-1);
	}
	
	
	@Test
	public void testGeneratePrimesUsingSieveofEratostheness(){
		List<Integer> list = service.generatePrimesUsingSieveofEratostheness(101);
		assertThat(list, hasSize(26));
	}
}
