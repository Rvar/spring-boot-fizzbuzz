package com.fizzbuzz.springbootfizzbuzz;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import model.FizzBuzzHitRequest;
import model.FizzBuzzRequest;

@RestController
@RequestMapping("/fizzbuzz")
public class FizzbuzzRestApi {
	
	/*
	 * mvn spring-boot:run
	 * ./mvnw test
	 * 
	 * */
	@Autowired
	private FizzbuzzServices fizzbuzzServices;
	
	@GetMapping
	public String getFizzBuzz(	@RequestParam int firstMultiple,
								@RequestParam int secondMultiple,
								@RequestParam int limit,
								@RequestParam String firstStr,
								@RequestParam String secondStr) {
		
		FizzBuzzRequest fbReq = new FizzBuzzRequest(firstMultiple, secondMultiple, limit, firstStr, secondStr);
		
		fizzbuzzServices.addFizzBuzzHitRequest(fbReq);
		return fizzbuzzServices.fizzbuzz(fbReq);
		
	}
	
	@GetMapping(value = "/mostRequested")
	public ResponseEntity<FizzBuzzHitRequest> getFizzBuzzStatistics() {
		
		try {
			return new ResponseEntity<>(fizzbuzzServices.getFizzBuzzMostRequested(), HttpStatus.OK);
		} catch(NoSuchElementException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No request have been made yet", ex);
		}
		
	}
}
