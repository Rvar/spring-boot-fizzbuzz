package com.fizzbuzz.springbootfizzbuzz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fizzbuzz.springbootfizzbuzz.model.FizzBuzzHitRequest;
import com.fizzbuzz.springbootfizzbuzz.model.FizzBuzzRequest;
import com.fizzbuzz.springbootfizzbuzz.services.FizzbuzzServices;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

@SpringBootTest
public class FizzbuzzServiceTests {
	
	@Autowired
	private FizzbuzzServices fizzbuzzService;
	
	/**
	 * Test all methods from FizzbuzzServices
	 */
	@Test
	public void testServiceFizzBuzz() {
		
		//Test Fizz Buzz algorithm
		String fizz = "fizz";
		String buzz = "buzz";
		FizzBuzzRequest fbReq = new FizzBuzzRequest(3, 5, 15, fizz, buzz);
		
		ArrayList<String> result = new ArrayList<String>();
		result.add("1");
		result.add("2");
		result.add(fizz);
		result.add("4");
		result.add(buzz);
		result.add(fizz);
		result.add("7");
		result.add("8");
		result.add(fizz);
		result.add(buzz);
		result.add("11");
		result.add(fizz);
		result.add("13");
		result.add("14");
		result.add(fizz + buzz);
		
		assertThat(fizzbuzzService.fizzbuzz(fbReq).hashCode())
		.isEqualTo(result.hashCode());
		
		//Test to add a hit on the previous request
		fizzbuzzService.addFizzBuzzHitRequest(fbReq);
		
		//Test to get the most requested
		FizzBuzzHitRequest fbHitReq = new FizzBuzzHitRequest(1, fbReq);
		assertThat(fizzbuzzService.getFizzBuzzMostRequested().getHits()).isEqualTo(fbHitReq.getHits());
		assertThat(fizzbuzzService.getFizzBuzzMostRequested().hashCode()).isEqualTo(fbHitReq.hashCode());
	}
}
