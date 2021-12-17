package com.fizzbuzz.springbootfizzbuzz.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Fizz-buzz parameter object request.
 * 
 * @author Ran
 *
 */
@FieldDefaults(level=AccessLevel.PRIVATE)
@AllArgsConstructor
@Data
public class FizzBuzzRequest {
	
	int firstMultiple;
	int secondMultiple;
	int limit;
	String firstStr;
	String secondStr;
}
