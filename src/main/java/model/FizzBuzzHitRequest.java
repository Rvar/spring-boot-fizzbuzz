package model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"hits", "fbReq"})
public class FizzBuzzHitRequest {
	
	int hits;
	FizzBuzzRequest fbReq;
	
	public void addHit(){
		this.hits += 1;
	}
}
