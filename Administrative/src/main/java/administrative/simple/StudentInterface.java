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
import administrative.Group;
import administrative.School;
import administrative.SchoolClass;
import administrative.Subject;



// Public Interface
public interface StudentInterface {   // Start Interface: StudentInterface
	
	// Public Getters
	public School getSchool();															// Method: getSchool					(Return the school the student is part of, single object)
	public String getName();															// Method: getName						(Return the name of the student)
	public String getPersonID();														// Method: getPersonId					(Return the person ID of the student)
	public Group getGroup();															// Method: getGroup						(Return the group the student is part of, single object)
	public boolean hasGroup();															// Method: hasGroup						(Check if the student is part of a group)
	public SchoolClass getSchoolClass();												// Method: getSchoolClass				(Return the school class the student is part of, single object)
	public ArrayList<Subject> getSubjects();											// Method: getSubjects					(Return all subject the student is part of, multiple objects)
	public Subject getSubject(int i);													// Method: getSubject					(Return a specific subject the student is part of, single object)
	public int totalSubjects();															// Method: totalSubjects				(Return the number of subject the student is part of)
	
	// Public Setters
	public void registerSubject(Subject subject);										// Start Method: registerSubject		(Add another subject the student is part of)
	
} // End Interface: StudentInterface
