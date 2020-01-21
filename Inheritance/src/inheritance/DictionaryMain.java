/*
 * DictionaryMain.java
 * Date: 4 nov 2008
 * Author: Jonas Lundberg
 */
package inheritance;

public class DictionaryMain {

	public static void main(String[] args) {
		
		Dictionary d = new Dictionary("Websters");
		d.setPages(876);
		d.setDefinitions(68345);		
		double ratio = d.computeRatio(); 
		
		System.out.println(d.toString());
		System.out.println("Definitions per page: " + ratio);
		
	}
	
}
