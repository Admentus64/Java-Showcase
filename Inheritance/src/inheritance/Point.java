/**
 * Point.java
 * Date: 2008-09-24
 * Author: Jonas Lundberg
 * 
 */
package inheritance;

/**
 * A simple point (x,y) implementation that
 * implements java.lang.Comparable. We also override
 * two methods inherited from the Object class
 *
 */

public class Point implements Comparable<Point> {
	
	private final int X;
	private final int Y;
	
	public Point(int x, int y)				{ X = x; Y = y; }
	public int getX()						{ return X; }
	public int getY()						{ return Y; }
	
	
	
	/* Overide two Object methods */ 
	@Override
	public String toString() {
		
		return "(" + X + "," + Y + ")";
		
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if (obj instanceof Point) {
			Point other = (Point)obj;
			return X == other.X && Y == other.Y;
		}
		else return false;
		
	}
	
	/* Implementerar Comparable. 
	 * return < 0 ==>  this < point. */
	public int compareTo(Point p) {
		
		if (X == p.X)
			return Y - p.Y;
		else return X - p.X;
		
	}
	
}
