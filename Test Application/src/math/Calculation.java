package math;

import java.util.Scanner;

class Calculation {
	
	static void checkCalculation(Scanner reader) {
		
		// Creates a reader instance which takes input from standard input - keyboard
		System.out.print("Enter two numbers, separated by space: ");
		
		// nextInt() reads the next integer from the keyboard
		float firstNumber = reader.nextFloat();
		float secondNumber = reader.nextFloat();
		
		// println() prints the following line to the output screen
		System.out.println("The sum of both numbers is: " + (firstNumber + secondNumber));
		
    }
	
}