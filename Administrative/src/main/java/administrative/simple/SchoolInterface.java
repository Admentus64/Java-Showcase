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
import administrative.GradeLevel;
import administrative.Group;
import administrative.SchoolClass;
import administrative.Student;
import administrative.Subject;



// Public Interface
public interface SchoolInterface {   // Start Interface: SchoolInterface
	
	// Public Getters
	public String getName();															// Method: getName						(Return the name of the school)
	public ArrayList<GradeLevel> getGradeLevels();										// Method: getGradeLevels				(Return all grade levels the school has, multiple objects)
	public GradeLevel getGradeLevel(int i);												// Method: getGradeLevel				(Return a specific grade level the school has, single object)
	public ArrayList<Group> getGroups();												// Method: getGroups					(Return all groups the school has, multiple objects)
	public Group getGroup(int i);														// Method: getGroup						(Return a specific group the school has, single object)
	public ArrayList<SchoolClass> getClasses();											// Method: getClasses					(Return all school classes the school has, multiple objects)
	public SchoolClass getClass(int i);													// Method: getClass						(Return a school class level the school has, single object)
	public ArrayList<Student> getStudents();											// Method: getStudents					(Return all students the school has, multiple objects)
	public Student getStudent(int i);													// Method: getStudent					(Return a student level the school has, single object)
	public ArrayList<Subject> getSubjects();											// Method: getSubjects					(Return all subjects the school has, multiple objects)
	public Subject getSubject(int i);													// Method: getSubject					(Return a subject level the school has, single object)
	
	// Public Methods	
	public boolean hasUngroupedStudents();												// Method: hasUngroupedStudent			(check if there are any students without a group)
	public int numberOfUngroupedStudents();												// Method: numberOfUngroupedStudents	(Return the number of students without a group)
	public ArrayList<Student> reportUngroupedStudents();								// Method: reportUngroupedStudents		(Return a list of all students without a group)
	
} // End Interface: SchoolInterface
