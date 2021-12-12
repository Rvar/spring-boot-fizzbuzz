package com.fizzbuzz.springbootfizzbuzz;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import model.FizzBuzzRequest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class FizzbuzzApplicationTests {
	
	@Autowired
	private FizzbuzzRestApi controller;
	
	/*@Autowired
	private FizzbuzzServices fizzbuzzService;*/

	@MockBean
	private FizzbuzzServices fizzbuzzServiceMock;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
	/*@Test
	public void testService() throws Exception {
		String fizz = "ahah";
		String buzz = "buzz";
		FizzBuzzRequest fbr = new FizzBuzzRequest(3, 5, 15, fizz, buzz);
		
		assertThat(fizzbuzzService.fizzbuzz(fbr))
		.isEqualTo("1,2," + fizz + ",4," + buzz + "," + fizz +",7,8," + fizz + ","+ buzz + ",11," + fizz + ",13,14," + fizz + buzz);
	}*/
	
	/*@Test
	 * Test pour tester tous les layer web
	 */
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		
		String fizz = "ahah";
		String buzz = "buzz";
		
		FizzBuzzRequest fbReq = new FizzBuzzRequest(3, 5, 15, "fizz", "buzz");
		
		String body = "{'firstMultiple':'3','secondMultiple':'5','limit':'100','firstStr':'fizz','secondStr':'buzz' }";
		
		when(fizzbuzzServiceMock.fizzbuzz(fbReq)).thenReturn("1,2," + fizz + ",4," + buzz + "," + fizz +",7,8," + fizz + ","+ buzz + ",11," + fizz + ",13,14," + fizz + buzz);
		
		this.mockMvc.perform(
				post("/fizzbuzz")
				.content(body)
				.contentType(MediaType.APPLICATION_JSON)
		        .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("Hello : 15")));
	}
	
	
}
