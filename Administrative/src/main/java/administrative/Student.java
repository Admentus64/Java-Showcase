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
import administrative.Group;
import administrative.School;
import administrative.SchoolClass;
import administrative.Subject;
import administrative.simple.StudentInterface;



// Public Class
public class Student implements StudentInterface {   // Start Class: Student
	
	// Private General Variables
	private Student self = this;
	private School school;
	
	// Private Variables
	private String name, personID;
	
	// Private Object Variables
	private Group group;
	private SchoolClass schoolClass;
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	
	
	
	// Constructor
	public Student(School school, String name, String personID) {   // Start Constructor: Student
		self.school = school;
		school.addStudent(self);
		self.name = name;
		self.personID = personID;
	} // End Constructor: Student
	
	
	
	// Public Getters
	public School getSchool()							{ return school; }						// Method: getSchool		(Return the school the student is part of, single object)
	public String getName()								{ return name; }						// Method: getName			(Return the name of the student)
	public String getPersonID()							{ return personID; }					// Method: getPersonId		(Return the person ID of the student)
	public Group getGroup()								{ return group; }						// Method: getGroup			(Return the group the student is part of, single object)
	public boolean hasGroup()							{ return group != null; }				// Method: hasGroup			(Check if the student is part of a group)
	public SchoolClass getSchoolClass()					{ return schoolClass; }					// Method: getSchoolClass	(Return the school class the student is part of, single object)
	public ArrayList<Subject> getSubjects()				{ return subjects; }					// Method: getSubjects		(Return all subject the student is part of, multiple objects)
	public Subject getSubject(int i)					{ return subjects.get(i); }				// Method: getSubject		(Return a specific subject the student is part of, single object)
	public int totalSubjects()							{ return subjects.size(); }				// Method: totalSubjects	(Return the number of subject the student is part of)
	
	
	
	// Public Setters
	// Add another subject the student is part of
	public void registerSubject(Subject subject) {   // Start Method: registerSubject
		
		if (!subjects.contains(subject))
			subjects.add(subject);
		
	} // End Method: registerSubject	
	
	
	
	// Package Setters
	void addToClass(SchoolClass schoolClass)			{ this.schoolClass = schoolClass; }		// Method: addToClass		(Change the school class the student is part of)
	
	// Change the group the student is part of
	void setGroup(Group group) {   // Start Method: setGroup
		
		if (group.getStudents().contains(self))
			this.group = group;
		
	} // End Class: setGroup
	
} // End Class: Student
