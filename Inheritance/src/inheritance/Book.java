/*
 * Book.java
 * Date: 4 nov 2008
 * Author: Jonas Lundberg
 */
package inheritance;

public class Book {
	
	protected int pages = 0;
	
	private String title;
	
	public Book(String t)					{ title = t; }
	public int getPages()					{ return pages; }
	public void setPages(int p)				{ pages = p; } 
	
	
	
	public String toString() {
		
		return "Title: " + title + ", Pages: " + pages;
		
	}
	
}
