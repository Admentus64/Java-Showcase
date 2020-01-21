package math;

import java.util.Scanner;

class Palindrome {
	
	static void checkPalindrome(Scanner reader) {
		
		// Creates a reader instance which takes input from standard input - keyboard
		System.out.print("Enter a palindrome number: ");
		
		int num = reader.nextInt();;
		int reversedInteger = 0, remainder, originalInteger = num;
		
		// Reversed integer is stored in variable
		while( num != 0 ) {
			remainder = num % 10;
			reversedInteger = reversedInteger * 10 + remainder;
			num  /= 10;
		}
		
		// Palindrome if orignalInteger and reversedInteger are equal
		if (originalInteger == reversedInteger)
			System.out.println(originalInteger + " is a palindrome.");
		else System.out.println(originalInteger + " is not a palindrome.");
		
	}
	
}