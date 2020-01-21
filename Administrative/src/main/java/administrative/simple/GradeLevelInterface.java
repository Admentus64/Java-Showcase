/*
 * Author: Robert Willem Hallink
 * Created: 28 November, 2019
 * Java, Maven Project
 * Open JDK 13.0.1, Eclipse 2019-09 4.13.0, Default Settings
 */



// Package
package administrative.simple;



// Import Java Library
import java.util.ArrayList;

// Imports
import administrative.School;
import administrative.SchoolClass;



// Public Interface
public interface GradeLevelInterface {   // Start Interface: GradeLevelInterface
	
	// Public Getters
	public School getSchool();															// Method: getSchool					(Return the school the grade level is part of, single object)
	public int getGrade();																// Method: getGrade						(Return the grade value of the grade level)
	public ArrayList<SchoolClass> getClasses();											// Method: getClasses					(Return all school classes the grade levels  has, multiple objects)
	public SchoolClass getClass(int i);													// Method: getClass						(Return a specific school class the grade level has, single object)
		
	// Public Setters
	public void addClass(SchoolClass schoolClass);										//  Method: addClass					(Add another school class the grade level has)
	
} // End Interface: GradeLevelInterface
