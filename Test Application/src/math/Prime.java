package math;

import java.util.Scanner;

class Prime {
	
	static void checkPrime(Scanner reader) {
		
		// Creates a reader instance which takes input from standard input - keyboard
		System.out.print("Enter a prime number: ");
		
		// nextInt() reads the next integer from the keyboard
		int num = reader.nextInt();;
		boolean isPrime = true;
		
		for (int i=2; i<=num/2; ++i) // condition for nonprime number
			if (num % i == 0) {
				isPrime = false;
				break;
			}
		
		if (isPrime)
			System.out.println(num + " is a prime number.");
		else System.out.println(num + " is not a prime number.");
		
	}
	
}