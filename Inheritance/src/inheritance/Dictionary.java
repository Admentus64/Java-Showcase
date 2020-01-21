/*
 * Dictionary.java
 * Date: 4 nov 2008
 * Author: Jonas Lundberg
 */
package inheritance;

/**
 * @author jonasl
 *
 */

public class Dictionary extends Book {
	
	private int definitions = 0;
	
	public Dictionary(String title)			{ super(title); }
	public void setDefinitions(int def)		{ definitions = def; }
	public int getDefinitions()				{ return definitions; }
	public double computeRatio()			{ return definitions / pages; }
	
	
	
	public String toString() {
		
		return super.toString() + ", Definitions: " + definitions;
		
	}
	
}
