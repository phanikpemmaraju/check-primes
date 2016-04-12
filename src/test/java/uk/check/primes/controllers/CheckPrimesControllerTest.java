package uk.check.primes.controllers;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.util.Arrays;
import java.util.List;

import uk.check.primes.MockDomainConfiguration;
import uk.check.primes.services.CheckPrimesService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { CheckPrimesController.class,MockDomainConfiguration.class })
@WebAppConfiguration
public class CheckPrimesControllerTest {
	
	@Autowired
	private CheckPrimesService service;
	
	@Autowired
	protected WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@After
	public void resetMocks() {
		reset(service);
	}
	
	@Test
	public void testGeneratePrimesTrailAlgoSingleThread() throws Exception{
		final String uri = "/primes/trailalgo/single/10";
		List<Integer> list = Arrays.asList(2,3,5,7);
		when(service.generatePrimesTrailAlgoSingleThread(Mockito.anyInt())).thenReturn(list);
		mockMvc.perform(get(uri)).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		mockMvc.perform(get(uri)).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON)).andDo(print());
	}
	
	@Test
	public void testGeneratePrimesTrailAlgoMultiThread() throws Exception{
		final String uri = "/primes/trailalgo/parallel/13";
		List<Integer> list = Arrays.asList(2,3,5,7,11,13);
		when(service.generatePrimesTrailAlgoMultiThread(Mockito.anyInt())).thenReturn(list);
		mockMvc.perform(get(uri)).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}

	
	@Test
	public void testGeneratePrimesSieveAlgo() throws Exception{
		final String uri = "/primes/sievealgo/11";
		List<Integer> list = Arrays.asList(2,3,5,7,11);
		when(service.generatePrimesUsingSieveofEratostheness(Mockito.anyInt())).thenReturn(list);
		mockMvc.perform(get(uri)).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	
	
}
