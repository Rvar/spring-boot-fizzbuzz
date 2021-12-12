package com.fizzbuzz.springbootfizzbuzz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.containsString;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;

import model.FizzBuzzHitRequest;
import model.FizzBuzzRequest;



@SpringBootTest
@AutoConfigureMockMvc
class FizzbuzzApplicationTests {
	
	@Autowired
	private FizzbuzzRestApi controller;

	@MockBean
	private FizzbuzzServices fizzbuzzServiceMock;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	/**
	 * Test web call for fizz-buzz request and most executed request.
	 * 
	 * @throws Exception
	 */
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		
		int firstMultiple = 2;
		int secondMultiple = 9;
		int limit = 30;
		String fizz = "fizz";
		String buzz = "buzz";
		
		FizzBuzzRequest fbReq_one = new FizzBuzzRequest(firstMultiple, secondMultiple, limit, fizz, buzz);
		
		executeFizzBuzz(fbReq_one);
		executeMostRequestedTest(fbReq_one);
	}
	
	/**
	 * Execute Fizz-buzz web call
	 * 
	 * @param fbReq_one : fizz-buzz parameter request
	 * @throws Exception
	 */
	private void executeFizzBuzz(FizzBuzzRequest fbReq_one) throws Exception {
		
		int firstMultiple = fbReq_one.getFirstMultiple();
		int secondMultiple = fbReq_one.getSecondMultiple();
		int limit = fbReq_one.getLimit();
		String fizz = fbReq_one.getFirstStr();
		String buzz = fbReq_one.getSecondStr();
		
		ArrayList<String> result = new ArrayList<String>();
		result.add("1"); result.add(fizz);
		result.add("3"); result.add(fizz);
		result.add("5"); result.add(fizz);
		result.add("7"); result.add(fizz);
		result.add(buzz); result.add(fizz);
		result.add("11"); result.add(fizz);
		result.add("13"); result.add(fizz);
		result.add("15"); result.add(fizz);
		result.add("17"); result.add(fizz + buzz);
		result.add("19"); result.add(fizz);
		result.add("21"); result.add(fizz);
		result.add("23"); result.add(fizz);
		result.add("25"); result.add(fizz);
		result.add(buzz); result.add("29");
		result.add(fizz);
		
		when(fizzbuzzServiceMock.fizzbuzz(fbReq_one)).thenReturn(result);
		
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("firstMultiple", String.valueOf(firstMultiple));
		requestParams.add("secondMultiple", String.valueOf(secondMultiple));
		requestParams.add("limit", String.valueOf(limit));
		requestParams.add("firstStr", fizz);
		requestParams.add("secondStr", buzz);
		
		this.mockMvc.perform(get("/fizzbuzz").params(requestParams))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("[\"" + String.join("\",\"", result) + "\"]")));
	}
	
	/**
	 * Execute Fizz-buzz most executed request call
	 * 
	 * @param fbReq_one : fizz-buzz parameter request
	 * @throws Exception
	 */
	private void executeMostRequestedTest(FizzBuzzRequest fbReq_one) throws Exception {
		
		FizzBuzzHitRequest fbHitRequest = new FizzBuzzHitRequest(1, fbReq_one);
		when(fizzbuzzServiceMock.getFizzBuzzMostRequested()).thenReturn(fbHitRequest);
		
		String expectedResult = "{\"hits\":1,\"fbReq\":{\"firstMultiple\":2,\"secondMultiple\":9,\"limit\":30,\"firstStr\":\"fizz\",\"secondStr\":\"buzz\"}}";
		
		this.mockMvc.perform(get("/fizzbuzz/mostRequested"))
		.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString(expectedResult)));
	}
	
}
