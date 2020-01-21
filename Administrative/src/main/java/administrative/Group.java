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
import administrative.Student;
import administrative.Subject;
import administrative.simple.GroupInterface;



// Public Class
public class Group implements GroupInterface {   // Start Class: Group
	
	// Private General Variables
	private Group self = this;
	private School school;
	
	// Private Variables
	private int minimumSize, maximumSize;
	
	// Private Object Variables
	private SchoolClass schoolClass;
	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	
	
	
	// Constructor
	public Group(School school, int minimumSize, int maximumSize) {   // Start Constructor: Group
		self.school = school;
		school.addGroup(self);
		self.minimumSize = minimumSize;
		self.maximumSize = maximumSize;
	} // End Constructor: Group
	
	
	
	// Public Getters
	public School getSchool()							{ return school; }						// Method: getSchool		(Return the school the school class is part of, single object)
	public int getMinimumSize()							{ return minimumSize; }					// Method: getMinimumSize	(Return the minimum group size)
	public int getMaximumSize()							{ return maximumSize; }					// Method: getMaximumSize	(Return the maximum group size)
	public SchoolClass getSchoolClass()					{ return schoolClass; }					// Method: getSchoolClass	(Return the school class the group is part of, single object)
	public ArrayList<Student> getStudents()				{ return students; }					// Method: getStudents		(Return all students the group has, multiple objects)
	public Student getStudent(int i)					{ return students.get(i); }				// Method: getStudent		(Return a specific student the group has, single object)
	public int size()									{ return students.size(); }				// Method: size				(Return the number of students the group has)
	public ArrayList<Subject> getSubjects()				{ return subjects; }					// Method: getSubjects		(Return all subjects the group has, multiple objects)
	public Subject getSubject(int i)					{ return subjects.get(i); }				// Method: getSubject		(Return a specific subject the group has, single object)
	public int totalSubjects()							{ return subjects.size(); }				// Method: totalSubjects	(Return the number of subjects the group has)

	
	
	
	// Public Setters
	// Add another subject the group has
	public void addSubject(Subject subject)	{   // Start Method: addSubject
		
		if (!subjects.contains(subject))
			subjects.add(subject);
		
	} // End Method: addSubject		
	
	
	
	// Package Setters
	void addToClass(SchoolClass schoolClass)			{ this.schoolClass = schoolClass; }		// Method: addToClass		(Change the school class the group is part of)
	
	
	
	// Public Methods
	public boolean isEmpty()							{ return students.size() == 0; }		// Method: isEmpty			(Check if the group has any students in it)
	
	// Start the group, but only if the amount of student in it is sufficient
	public boolean startStudy() {   // Start Method: startStudy
		
		// The group should be part of a school class
		if (schoolClass == null)
			return false;
		
		// Make sure the group is not empty, not too small or too big
		if (size() == 0)
			return false;
		if (totalSubjects() == 0)
			return false;
		if (size() > maximumSize)
			return false;
		if (size() < minimumSize)
			return false;
		
		// Check if each student in the group studies a subject as well
		for (int i=0; i<size(); i++)
			if (students.get(i).totalSubjects() == 0)
				return false;
		
		return true;
		
	} // End Method: startStudy
	
	// Fill the group with students that follow all courses that the group contains
	public ArrayList<Student> populate() {   // Start Method: populate
		
		// If the group is not linked to a school class then it can not populate
		if (getSchoolClass() == null)
			return null;
		
		// Initialize variables
		ArrayList<Student> temp = new ArrayList<Student>();
		boolean addNext = true;
		
		// Go through each student, check if they are suitable for the group (has all same subjects and not more as the group), then add it into the group temporary
		for (Student student : school.getStudents()) {
			addNext = true;
			
			if (student.getSchoolClass() != getSchoolClass())		// Student requires to be part of a class
				addNext = false;
			if (student.hasGroup())									// Student can only be part of one group
				addNext = false;
			if (student.totalSubjects() != totalSubjects())			// Student require the same amount of subjects as the group
				addNext = false;
			for (Subject subject : subjects)						// Student requires the same subjects as the group
				if (!student.getSubjects().contains(subject)) {
					addNext = false;
					break;
				}
			
			// If the student is approved, proceed
			if (addNext)
				temp.add(student);
		}
		
		// Check if the amount of students is not too few or too many, otherwise abort the population of the group
		if (temp.size() < minimumSize || temp.size() > maximumSize)
			return null;
		
		// Make the students in the group permanent
		students = temp;
		
		// Go through each student and link them to the same group
		for (Student student : students) {
			student.setGroup(self);
			for (Subject subject : subjects)						// Go through each subject and link them to the same students
				subject.addStudent(student);
		}
		
		return students;
		
	} // End Method: populate
	
} // End Class: Group
