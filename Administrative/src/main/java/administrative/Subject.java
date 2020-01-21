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
import administrative.Student;
import administrative.simple.SubjectInterface;



//Public Class
public class Subject implements SubjectInterface {   // Start Class: Subject
	
	// Private General Variables
	private Subject self = this;
	private School school;
	
	// Private Variables
	private String name;
	
	// Private Object Variables
	private ArrayList<Student> students = new ArrayList<Student>();
	
	
	
	// Constructor
	public Subject(School school, String name) {   // Start Constructor: Subject
		self.school = school;
		school.addSubject(self);
		self.name = name;
	} // End Constructor: Subject
	
	
	
	// Public Getters
	public School getSchool()							{ return school; }						// Method: getSchool		(Return the school the subject is part of, single object)
	public String getName()								{ return name; }						// Method: getName			(Return the name of the subject)
	public ArrayList<Student> getStudents()				{ return students; }					// Method: getStudents		(Return all students the subject has, multiple objects)
	public Student getStudent(int i)					{ return students.get(i); }				// Method: getStudent		(Return a specific student the subject has, single object)
	
	
	
	// Package Setters
	// Add another student that follows the subject
	void addStudent(Student student) {   // Start Method: addStudent
		
		if (!students.contains(student))
			students.add(student);
		
	} // End Method: addStudent
	
	
	
	// Public Methods
	public boolean isMandatory()						{ return false; }						// Method: isMandatory		(Return false, the subject is not mandatory)
	
} // End Class: Subject
