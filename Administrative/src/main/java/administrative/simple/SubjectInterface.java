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
import administrative.Student;



// Public Interface
public interface SubjectInterface {   // Start Interface: SubjectInterface
	
	// Public Getters
	public School getSchool();															// Method: getSchool					(Return the school the subject is part of, single object)
	public String getName();															// Method: getName						(Return the name of the subject)
	public ArrayList<Student> getStudents();											// Method: getStudents					(Return all students the subject has, multiple objects)
	public Student getStudent(int i);													// Method: getStudent					(Return a specific student the subject has, single object)
	
	// Public Methods
	public boolean isMandatory();														// Method: isMandatory					(Return false, the subject is not mandatory)
	
} // End Interface: SubjectInterface
