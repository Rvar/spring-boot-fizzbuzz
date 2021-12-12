package com.fizzbuzz.springbootfizzbuzz;

import java.util.HashMap;

import org.springframework.core.convert.ConversionFailedException;
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
	 * @return String result
	 */
	public String fizzbuzz(FizzBuzzRequest fbReq)  {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= fbReq.getLimit(); ++i) {
			
			if(i%fbReq.getFirstMultiple() == 0 && i%fbReq.getSecondMultiple() == 0) {
				sb.append(fbReq.getFirstStr() + fbReq.getSecondStr());
			} else if(i%fbReq.getFirstMultiple() == 0) {
				sb.append(fbReq.getFirstStr());
			} else if(i%fbReq.getSecondMultiple() == 0) {
				sb.append(fbReq.getSecondStr());
			} else {
				sb.append(i);
			}
			
			sb.append(",");
		}
		
		return sb.substring(0, sb.length()-1);
	}
	
	
	public void addFizzBuzzHitRequest(FizzBuzzRequest fbReq) {
		
		Integer hc = fbReq.hashCode();
		
		if(fizzBuzzHitRequest.containsKey(hc)) {
			fizzBuzzHitRequest.get(hc).addHit();
		} else {
			fizzBuzzHitRequest.put(hc, new FizzBuzzHitRequest(0, fbReq));
		}
	}
	
	public FizzBuzzHitRequest getFizzBuzzMostRequested() {
		
		return fizzBuzzHitRequest.entrySet()
		.stream()
		.max((fBHitReq1, fBHitReq2) -> fBHitReq1.getValue().getHits()  > fBHitReq2.getValue().getHits() ? 1 : -1)
		.get().getValue();
	}
}
