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
import administrative.Student;
import administrative.Subject;



// Public Interface
public interface GroupInterface {   // Start Interface: GroupInterface
	
	// Public Getters
	public School getSchool();															// Method: getSchool						(Return the school the school class is part of, single object)
	public int getMinimumSize();														// Method: getMinimumSize					(Return the minimum group size)
	public int getMaximumSize();														// Method: getMaximumSize					(Return the maximum group size)
	public SchoolClass getSchoolClass();												// Method: getSchoolClass					(Return the school class the group is part of, single object)
	public ArrayList<Student> getStudents();											// Method: getStudents						(Return all students the group has, multiple objects)
	public Student getStudent(int i);													// Method: getStudent						(Return a specific student the group has, single object)
	public int size();																	// Method: size								(Return the number of students the group has)
	public ArrayList<Subject> getSubjects();											// Method: getSubjects						(Return all subjects the group has, multiple objects)
	public Subject getSubject(int i);													// Method: getSubject						(Return a specific subject the group has, single object)
	public int totalSubjects();															// Method: totalSubjects					(Return the number of subjects the group has)
	
	// Public Setters
	public void addSubject(Subject subject);											// Method: addSubject						(Add another subject the group has)
		
	// Public Methods
	public boolean isEmpty();															// Method: isEmpty							(Check if the group has any students in it)
	public boolean startStudy();														// Method: startStudy						(Start the group, but only if the amount of student in it is sufficient)
	public ArrayList<Student> populate();												// Method: populate							(Fill the group with students that follow all courses that the group contains)
	
} // End Interface: GroupInterface
