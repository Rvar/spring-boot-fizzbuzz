package model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Fizz-buzz hit request parameter object.
 * 
 * @author Ran
 *
 */
@AllArgsConstructor
@Data
public class FizzBuzzHitRequest {
	
	int hits;
	FizzBuzzRequest fbReq;
	
	public void addHit(){
		this.hits += 1;
	}
}