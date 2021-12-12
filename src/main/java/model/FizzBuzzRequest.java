package model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Fizz-buzz parameter object request.
 * 
 * @author Ran
 *
 */
@FieldDefaults(level=AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of= {"firstMultiple", "secondMultiple", "limit", "firstStr", "secondStr"})
@ToString(of= {"firstMultiple", "secondMultiple", "limit", "firstStr", "secondStr"})
public class FizzBuzzRequest {
	
	int firstMultiple;
	int secondMultiple;
	int limit;
	String firstStr;
	String secondStr;
}
