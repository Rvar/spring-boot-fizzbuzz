package com.fizzbuzz.springbootfizzbuzz;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class SpringBootFizzbuzzApplicationTests {
	
	@Autowired
	private SpringBootFizzbuzzRestApi controller;
	
	/*@MockBean
	private SpringBootFizzbuzzServices fizzbuzzService;
	
	@Autowired
	private MockMvc mockMvc;*/
	
	@Autowired
	private SpringBootFizzbuzzServices fizzbuzzService;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	@Test
	public void testService() throws Exception {
		String fizz = "ahah";
		String buzz = "buzz";
		assertThat(fizzbuzzService.fizzbuzz(3, 5, 15, fizz, buzz))
		.isEqualTo("1,2," + fizz + ",4," + buzz + "," + fizz +",7,8," + fizz + ","+ buzz + ",11," + fizz + ",13,14," + fizz + buzz);
		
	}
	
	/*@Test
	 * Test pour tester tous les layer web
	public void shouldReturnDefaultMessage() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("firstNumber", "5");
		
		when(fizzbuzzService.helloWorld(5)).thenReturn("Hello : 15");
		
		this.mockMvc.perform(get("/fizzbuzz").params(params))
		.andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Hello : 15")));
	}*/
	
	
}
