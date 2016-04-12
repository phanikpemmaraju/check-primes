package uk.check.primes.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import uk.check.primes.exceptions.DataException;
import uk.check.primes.services.CheckPrimesService;

@RestController
@RequestMapping(path="/primes",produces = {"application/json"})
public class CheckPrimesController {
	
	@Autowired
	private CheckPrimesService service;
				
	@RequestMapping(path="/trailalgo/single/{range}",method = RequestMethod.GET)
	public ResponseEntity<?> generatePrimesTrailAlgoSingleThread(@PathVariable Integer range) throws DataException{
		final List<Integer> primeRange = service.generatePrimesTrailAlgoSingleThread(range);
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(primeRange);				
	}
	
	@RequestMapping(path="/trailalgo/parallel/{range}",method = RequestMethod.GET)
	public ResponseEntity<?> generatePrimesTrailAlgoMultiThread(@PathVariable Integer range) throws DataException{
		final List<Integer> primeRange = service.generatePrimesTrailAlgoMultiThread(range);
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(primeRange);				
	}
	
	@RequestMapping(path="/sievealgo/{range}",method = RequestMethod.GET)
	public ResponseEntity<?> generatePrimesSieveAlgo(@PathVariable Integer range) throws DataException{
		final List<Integer> primeRange = service.generatePrimesUsingSieveofEratostheness(range);
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(primeRange);
	}

}
