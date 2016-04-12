package uk.check.primes.exceptionhandlers;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import uk.check.primes.common.ErrorMessage;
import uk.check.primes.exceptions.DataException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class CheckPrimesControllerAdvice extends ResponseEntityExceptionHandler{
	
	@Autowired
	private ExceptionMapper exceptionMapper;	
		
	@ExceptionHandler(value = {DataException.class})
	public ResponseEntity<List<ErrorMessage>> handleDataException(DataException dataException) {
        List<ErrorMessage> errors = new ArrayList<>();
        errors.add(exceptionMapper.getException(dataException, HttpStatus.BAD_REQUEST));
        return new ResponseEntity<List<ErrorMessage>>(errors,HttpStatus.BAD_REQUEST);
    }
	

}
