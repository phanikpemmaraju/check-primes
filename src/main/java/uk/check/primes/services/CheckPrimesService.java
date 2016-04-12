package uk.check.primes.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import uk.check.primes.exceptions.DataException;

@Service
public class CheckPrimesService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${service.threads}")
	private int threads;
	
	public void setThreads(int threads){
		this.threads = threads;
	}
	
	// Trial Division Algorithm;
	public List<Integer> generatePrimesTrailAlgoSingleThread(Integer range) throws DataException {
		// TODO Auto-generated method stub
		if (range <= 1)
			throw new DataException("400", "Invalid Number entered,should be a positive number > 1");

		final List<Integer> primes = new ArrayList<>();
		long timeStartFuture = Calendar.getInstance().getTimeInMillis();

		IntStream.rangeClosed(2, range.intValue()).forEach(prime -> {
			if (isPrime(prime) == true)
				primes.add(prime);
		});

		long timeEndFuture = Calendar.getInstance().getTimeInMillis();
		long timeNeededFuture = timeEndFuture - timeStartFuture;
		logger.info("Result for Trial Division Algo for a Single Thread :  calculated in " + timeNeededFuture + " ms");
		return primes;
	}

	// Trial Division Algorithm;
	public List<Integer> generatePrimesTrailAlgoMultiThread(Integer range) throws DataException {
		// TODO Auto-generated method stub
		if (range <= 1)
			throw new DataException("400", "Invalid Number entered,should be a positive number > 1");

		final List<Integer> primes = new ArrayList<>();
		long timeStartFuture = Calendar.getInstance().getTimeInMillis();

		final ExecutorService executor = Executors.newFixedThreadPool(threads);
		final List<Callable<List<Integer>>> callables = new ArrayList<>();
		final int divisor = range / threads; final int mod = range % threads;
		for (int i = 0; i < threads; i++) {
			final int start = i;
			callables.add(new Callable<List<Integer>>() {
				@Override
				public List<Integer> call() throws Exception {
					// TODO Auto-generated method stub
					return generatePrimesUsingTrialDivision((start * divisor),
							((start + 1) * divisor));
				}
			});
		}
		if (mod > 0){
			callables.add(new Callable<List<Integer>>() {
				@Override
				public List<Integer> call() throws Exception {
					// TODO Auto-generated method stub
					return generatePrimesUsingTrialDivision((threads * divisor),
							((threads * divisor) + mod));
				}
			});
		}

		try {			
			executor.invokeAll(callables)
			.stream()
			.forEach(future -> {
				try {
					future.get().forEach(prime -> primes.add(prime));
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long timeEndFuture = Calendar.getInstance().getTimeInMillis();
		long timeNeededFuture = timeEndFuture - timeStartFuture;
		logger.info("Result for Trial Division Algo for a Multi Thread :  calculated in  " + timeNeededFuture + " ms");
		executor.shutdown();
		try {
			executor.awaitTermination(2, TimeUnit.MILLISECONDS);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return primes;
	}	

	// Returns list of prime numbers using Trial division Algorithm for a given range
	public List<Integer> generatePrimesUsingTrialDivision(int start, int end) throws Exception{
		
		final List<Integer> list = new ArrayList<>();
		for (int i = start; i <= end; i++) {
			if (isPrime(i)) {
				list.add(i);
			}
		}
		return list;
	}

	// Trial Division Algorithm;
	public boolean isPrime(int number) {
		if (number <= 1)
			return false;
		boolean prime = true;
		for (int i = 2; i <= Math.sqrt(number); i++) {
			if (number % i == 0) {
				prime = false;
				break;
			}
		}
		return prime;
	}

	// Sieve of Eratostheness Algorithm
	public List<Integer> generatePrimesUsingSieveofEratostheness(Integer range)
			throws DataException {
		if (range <= 1)
			throw new DataException("400", "Invalid Number entered,should be a positive number > 1");
		long timeStartFuture = Calendar.getInstance().getTimeInMillis();
		final List<Integer> primeNumbers = new ArrayList<>();
		Boolean[] primes = new Boolean[range.intValue() + 1];

		// Assume all the numbers are prime numbers.
		for (int i = 0; i <= range; i++) {
			primes[i] = true; // O(n)
		}

		// Numbers 0 & 1 are not prime numbers.
		primes[0] = false;
		primes[1] = false;

		for (int i = 2; i <= Math.sqrt(range.intValue()); i++) {
			if (primes[i] == true) {
				for (int j = 2; i * j <= range.intValue(); j++) {
					primes[i * j] = false;
				}
			}
		}

		for (int i = 0; i < primes.length; i++) {
			if (primes[i] == true)
				primeNumbers.add(i);
		}
		long timeEndFuture = Calendar.getInstance().getTimeInMillis();
		long timeNeededFuture = timeEndFuture - timeStartFuture;
		logger.info( "Result for SieveofEratostheness (Future):  calculated in " + timeNeededFuture + " ms");
		return primeNumbers;
	}

}
