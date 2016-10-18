package net.saisimon.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 412. Fizz Buzz
 * 	Write a program that outputs the string representation of numbers from 1 to n.
 * 	But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
 * 	For numbers which are multiples of both three and five output “FizzBuzz”.
 * 
 * 	Example:
 * 		n = 15,
 * 		Return:
 * 		[
 *		    "1",
 *		    "2",
 *		    "Fizz",
 *	 	    "4",
 *		    "Buzz",
 *		    "Fizz",
 *		    "7",
 *		    "8",
 *		    "Fizz",
 *		    "Buzz",
 *		    "11",
 *		    "Fizz",
 *		    "13",
 *		    "14",
 *		    "FizzBuzz"
 *		]
 * 
 * @author Saisimon
 *
 */
public class FizzBuzz {
	public List<String> fizzBuzz(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be greater or equal to zero.");
		}
        List<String> res = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
			res.add(i % 5 == 0 && i % 3 == 0 ? "FizzBuzz" : i % 3 == 0 ? "Fizz" : i % 5 == 0 ? "Buzz" : i + "");
		}
        return res;
    }
}
