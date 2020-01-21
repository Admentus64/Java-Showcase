package math;

import java.util.Scanner;

public class DoMath {
	
	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);
		
		Calculation.checkCalculation(reader);
		Prime.checkPrime(reader);
		Palindrome.checkPalindrome(reader);
		
		reader.close();
		
	}
	
}
