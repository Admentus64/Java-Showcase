/**
 * 
 */
package inheritance;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple driver that tests the the Point class.
 * Point implements java.lang.Comparable ==> A list
 * of Points can be sorted using Collections.sort(). 
 * 
 * @author jonasl
 *
 */

public class PointMain {

	public static void main(String[] args) {
		
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(new Point(3, 3));
		list.add(new Point(3, 2));
		list.add(new Point(5, 4));
		list.add(new Point(1, 3));
		list.add(new Point(1, 2));
		
		for (Point p : list)
			System.out.print(p + " ");  // Calls toString() implicitely
		System.out.println("\n");
		
		/* Check to see if equals() work */
		if (list.contains(new Point(5, 4)))
			System.out.println("The equals() method work\n");
		
		/* Sort list according to Comparable */
		Collections.sort(list); 
		
		/* Apply toString directly on list */
		System.out.print(list);
		
	}
	
}
