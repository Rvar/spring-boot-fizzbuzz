package com.fizzbuzz.springbootfizzbuzz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import model.FizzBuzzHitRequest;
import model.FizzBuzzRequest;

@Service
public class FizzbuzzServices {
	
	/**
	 * Key : hashCode request
	 * Value : number of hits for this request
	 */
	private HashMap<Integer, FizzBuzzHitRequest> fizzBuzzHitRequest = new HashMap<Integer, FizzBuzzHitRequest>();
	
	/**
	 * Fizz-Buzz algorithm 
	 * 
	 * @param fbReq : FizzBuzzRequest with attributs :  
			int firstMultiple,
			int secondMultiple,
			int limit,
			String firstStr,
			String secondStr
	 * @return ArrayList<String> ArrayList of String
	 */
	public ArrayList<String> fizzbuzz(FizzBuzzRequest fbReq) throws NoSuchElementException  {
		
		ArrayList<String> result = new ArrayList<>();
		
		if(fbReq.getLimit() < 1) {
			throw new NoSuchElementException("Param : limit can not be under than 1");
		}
		
		for(int i = 1; i <= fbReq.getLimit(); ++i) {
			
			if(i%fbReq.getFirstMultiple() == 0 && i%fbReq.getSecondMultiple() == 0) {
				result.add(fbReq.getFirstStr() + fbReq.getSecondStr());
			} else if(i%fbReq.getFirstMultiple() == 0) {
				result.add(fbReq.getFirstStr());
			} else if(i%fbReq.getSecondMultiple() == 0) {
				result.add(fbReq.getSecondStr());
			} else {
				result.add(String.valueOf(i));
			}
		}
		
		return result;
	}
	
	
	/**
	 * Add hit to a fizz-buzz request
	 * @param fbReq : fizz-buzz request to process
	 */
	public void addFizzBuzzHitRequest(FizzBuzzRequest fbReq) {
		
		Integer hc = fbReq.hashCode();
		
		if(this.fizzBuzzHitRequest.containsKey(hc)) {
			this.fizzBuzzHitRequest.get(hc).addHit();
		} else {
			this.fizzBuzzHitRequest.put(hc, new FizzBuzzHitRequest(1, fbReq));
		}
	}
	
	/**
	 * Get the most executed fizz-buzz request contain in fizzBuzzHitRequest
	 * @return FizzBuzzHitRequest
	 */
	public FizzBuzzHitRequest getFizzBuzzMostRequested() {
		
		return this.fizzBuzzHitRequest.entrySet()
		.stream()
		.max((fBHitReq1, fBHitReq2) -> fBHitReq1.getValue().getHits()  > fBHitReq2.getValue().getHits() ? 1 : -1)
		.get().getValue();
	}
}
