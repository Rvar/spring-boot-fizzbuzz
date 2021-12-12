package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FizzBuzzHitRequest {
	
	int hits;
	FizzBuzzRequest fbReq;
	
	public void addHit(){
		this.hits += 1;
	}
}
