# spring-boot-fizzbuzz
Fizz-Buzz Project REST Server

The original fizz-buzz consists in writing all numbers from 1 to 100, and just replacing all multiples of 3 by "fizz", all multiples of 5 by "buzz", and all multiples of 15 by "fizzbuzz". The output would look like this: "1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16,...".

 

## Two REST API endpoints :

### GET
```/fizzbuzz``` : Accepts five parameters : three integers firstMultiple, secondMultiple and limit, and two strings firstStr and secondStr.

Returns a list of strings with numbers from 1 to limit, where: all multiples of firstMultiple are replaced by firstStr, all multiples of secondMultiple are replaced by secondStr, 
all multiples of firstMultiple and secondMultiple are replaced by firstStrsecondStr.

Example :

```/fizzbuzz?firstMultiple=9&secondMultiple=6&limit=100&firstStr=fizz&secondStr=buzz```
 
### GET
```/fizzbuzz/mostRequested```: statistics endpoint allowing users to know what the most frequent request has been. No parameter.

Return the parameters corresponding to the most used request, as well as the number of hits for this request

# Start server :
```./mvnw spring-boot:run```

# Execute unit and web tests :
```./mvnw test```
