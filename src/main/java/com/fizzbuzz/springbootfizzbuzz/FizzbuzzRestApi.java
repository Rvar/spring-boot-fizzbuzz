package com.fizzbuzz.springbootfizzbuzz;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import model.FizzBuzzHitRequest;
import model.FizzBuzzRequest;

@RestController
@RequestMapping("/fizzbuzz")
public class FizzbuzzRestApi {
	
	@Autowired
	private FizzbuzzServices fizzbuzzServices;
	
	/**
	 * Operation GET for fizz-buzz
	 * 
	 * @param firstMultiple
	 * @param secondMultiple
	 * @param limit : can not be under than 1
	 * @param firstStr
	 * @param secondStr
	 * @return list of strings with numbers from 1 to limit
	 * 	where: all multiples of firstMultiple are replaced by firstStr, 
	 * 	all multiples of secondMultiple are replaced by secondStr, 
	 * 	all multiples of firstMultiple and secondMultiple are replaced by firstStrsecondStr.
	 */
	@GetMapping
	public ArrayList<String> getFizzBuzz(	
								@RequestParam int firstMultiple,
								@RequestParam int secondMultiple,
								@RequestParam int limit,
								@RequestParam String firstStr,
								@RequestParam String secondStr) {
		
		FizzBuzzRequest fbReq = new FizzBuzzRequest(firstMultiple, secondMultiple, limit, firstStr, secondStr);
		
		fizzbuzzServices.addFizzBuzzHitRequest(fbReq);
		
		try {
			return fizzbuzzServices.fizzbuzz(fbReq);
		} catch(NoSuchElementException ex) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
		}
		
	}
	
	/**
	 * Operation GET for most requested request
	 * 
	 * @return the parameters corresponding to the most used request, as well as the number of hits for this request
	 */
	@GetMapping(value = "/mostRequested")
	public FizzBuzzHitRequest getFizzBuzzMostRequested() {
		
		try {
			return fizzbuzzServices.getFizzBuzzMostRequested();
		} catch(NoSuchElementException ex) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No request have been made yet");
		}
		
	}
}
