==========================================================================
                  Check Prime Numbers
==========================================================================

Check Primes is a Spring Boot MicroService Example which runs on port 9191.
This example demonstrates how to generate prime numbers within a range provided using Trial Division Algorithm and Sieve of Eratostheness Algorithms.
This example also uses the implementation of multi threading.

Contents

The mapping of the URI path space is presented in the following table:

URI path	Resource class	HTTP methods
/single/{range}	SpringRequestResource	GET
/parallel/{range}	SpringRequestResource	GET
/sievealgo/{range}	SpringRequestResource	GET

Running the Example

Run the example as follows:

check-primes> mvn clean install
check-primes> java -jar target\check-primes-0.0.1-SNAPSHOT.jar


http://localhost:9191/primes/trailalgo/single/{range}
http://localhost:9191/primes/trailalgo/parallel/{range}
http://localhost:9191/primes/sievealgo/{range}

Example URLs are as follows:

http://localhost:9191/primes/trailalgo/single/101
http://localhost:9191/primes/trailalgo/parallel/101
http://localhost:9191/primes/sievealgo/101