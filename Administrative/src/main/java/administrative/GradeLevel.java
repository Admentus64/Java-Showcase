/*
 * Author: Robert Willem Hallink
 * Created: 28 November, 2019
 * Java, Maven Project
 * Open JDK 13.0.1, Eclipse 2019-09 4.13.0, Default Settings
 */



// Package
package administrative;



// Import Java Library
import java.util.ArrayList;

// Imports
import administrative.School;
import administrative.SchoolClass;
import administrative.simple.GradeLevelInterface;



// Public Class
public class GradeLevel implements GradeLevelInterface {   // Start Class: GradeLevel
	
	// Private General Variables
	private GradeLevel self = this;
	private School school;
	
	// Private Variables
	private int value;
	
	// Private Object Variables
	private ArrayList<SchoolClass> classes = new ArrayList<SchoolClass>();
	
	
	
	// Constructor
	public GradeLevel(School school, int value) {   // Start Constructor: GradeLevel
		self.school = school;
		school.addGradeLevel(self);
		self.value = value;
	} // End Constructor: GradeLevel
	
	
	
	// Public Getters
	public School getSchool()							{ return school; }						// Method: getSchool		(Return the school the grade level is part of, single object)
	public int getGrade()								{ return value; }						// Method: getGrade			(Return the grade value of the grade level)
	public ArrayList<SchoolClass> getClasses()			{ return classes; }						// Method: getClasses		(Return all school classes the grade levels  has, multiple objects)
	public SchoolClass getClass(int i)					{ return classes.get(i); }				// Method: getClass			(Return a specific school class the grade level has, single object)
	
	
	
	// Public Setters
	// Add another school class the grade level has
	public void addClass(SchoolClass schoolClass) {   // Start Method: addClass
		
		if (!classes.contains(schoolClass))
			classes.add(schoolClass);
		
	} // End Method: addClass
	
} // End Class: GradeLevel
