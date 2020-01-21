/*
 * Author: Robert Willem Hallink
 * Created: 28 November, 2019
 * Java, Maven Project
 * Open JDK 13.0.1, Eclipse 2019-09 4.13.0, Default Settings
 */



// Package
package administrative;



//Import Java Library
import java.util.ArrayList;

// Imports
import administrative.GradeLevel;
import administrative.Group;
import administrative.SchoolClass;
import administrative.Student;
import administrative.Subject;
import administrative.simple.SchoolInterface;



// Public Class
public class School implements SchoolInterface {   // Start Class: School
	
	// Private Variables
	private String name;
	
	// Private Object Variables
	private ArrayList<GradeLevel> gradeLevels = new ArrayList<GradeLevel>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<SchoolClass> classes = new ArrayList<SchoolClass>();
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	
	
	
	// Constructor
	public School(String name) {   // Start Constructor: School
		this.name = name;
	} // End Constructor: School
	
	
	
	// Public Getters
	public String getName()								{ return name; }						// Method: getName			(Return the name of the school)
	public ArrayList<GradeLevel> getGradeLevels()		{ return gradeLevels; }					// Method: getGradeLevels	(Return all grade levels the school has, multiple objects)
	public GradeLevel getGradeLevel(int i)				{ return gradeLevels.get(i); }			// Method: getGradeLevel	(Return a specific grade level the school has, single object)
	public ArrayList<Group> getGroups()					{ return groups; }						// Method: getGroups		(Return all groups the school has, multiple objects)
	public Group getGroup(int i)						{ return groups.get(i); }				// Method: getGroup			(Return a specific group the school has, single object)
	public ArrayList<SchoolClass> getClasses()			{ return classes; }						// Method: getClasses		(Return all school classes the school has, multiple objects)
	public SchoolClass getClass(int i)					{ return classes.get(i); }				// Method: getClass			(Return a school class level the school has, single object)
	public ArrayList<Student> getStudents()				{ return students; }					// Method: getStudents		(Return all students the school has, multiple objects)
	public Student getStudent(int i)					{ return students.get(i); }				// Method: getStudent		(Return a student level the school has, single object)
	public ArrayList<Subject> getSubjects()				{ return subjects; }					// Method: getSubjects		(Return all subjects the school has, multiple objects)
	public Subject getSubject(int i)					{ return subjects.get(i); }				// Method: getSubject		(Return a subject level the school has, single object)
	
	
	
	// Package Setters
	// Add another grade level the school has
	void addGradeLevel(GradeLevel gradeLevel) {   // Start Method: addGradeLevel
		
		if (!gradeLevels.contains(gradeLevel));
			gradeLevels.add(gradeLevel);
		
	} // End Method: addGradeLevel
	
	// Add another group the school has
	void addGroup(Group group) {   // Start Method: addGroup
		
		if (!groups.contains(group));
			groups.add(group);
		
	} // End Method: addGroup
	
	// Add another school class the school has
	void addClass(SchoolClass schoolClass) {   // Start Method: addClass
		
		if (!classes.contains(schoolClass));
			classes.add(schoolClass);
		
	} // End Method: addClass
	
	// Add another student the school has
	void addStudent(Student student) {   // Start Method: addStudent
		
		if (!students.contains(student));
			students.add(student);
		
	} // End Method: addStudent
	
	// Add another subject the school has
	void addSubject(Subject subject) {   // Start Method: addSubject
		
		if (!subjects.contains(subject));
			subjects.add(subject);
		
	} // End Method: addSubject
	
	
	
	// Package methods
	// Remove a group from the school
	void removeGroup(Group group) {   // Start Method: removeGroup
		
		if (groups.contains(group))
			groups.remove(group);
		
	} // End Method: removeGroup
	
	
	
	// Public Methods	
	public boolean hasUngroupedStudents()				{ return reportUngroupedStudents() != null; }	// Method: hasUngroupedStudent (check if there are any students without a group)
	
	// Return the number of students without a group
	public int numberOfUngroupedStudents() {   // Start Method: numberOfUngroupedStudents
		
		// Return the value 0 if there is no list with students which are not in a group
		if (reportUngroupedStudents() == null)
			return 0;
		// Return the size of the list with the students which are not in a group
		return reportUngroupedStudents().size();
		
	} // End Method: numberOfUngroupedStudent
	
	// Return a list of all students without a group
	public ArrayList<Student> reportUngroupedStudents() {   // Start Method: reportUngroupedStudents
		
		// New list to report students without a group
		ArrayList<Student> ungroupedStudents = new ArrayList<Student>();
		
		// Go through every student and check if they are without a group, if so include them
		for (Student student : students)
			if (!student.hasGroup())
				ungroupedStudents.add(student);
		
		// If each student is part of a group then there is no need to send an empty list, so return a null
		if (ungroupedStudents.size() > 0)
			return ungroupedStudents;
		return null;
		
	} // End Method: reportUngroupedStudents
	
} // End Class: School
