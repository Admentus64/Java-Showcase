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
import administrative.School;
import administrative.Student;



// Public Interface
public interface SchoolClassInterface {   // Start Interface: SchoolClassInterface
	
	// Public Getters
	public School getSchool();															// Method: getSchool					(Return the school the school class is part of, single object)
	public String getName();															// Method: getName						(Return the name of the school class)
	public ArrayList<GradeLevel> getGradeLevels();										// Method: getGradeLevels				(Return all grade levels the school class has, multiple objects)
	public GradeLevel getGradeLevel(int i);												// Method: getGradeLevel				(Return a specific grade level the school class has, single object)
	public ArrayList<Group> getGroups();												// Method: getGroups					(Return all groups the school class has, multiple objects)
	public Group getGroup(int i);														// Method: getGroup						(Return a group the school class has, single object)
	public ArrayList<Student> getStudents();											// Method: getStudents					(Return all students the school class has, multiple objects)
	public Student getStudent(int i);													// Method: getStudent					(Return a specific student the school class has, single object)
	public int size();																	// Method: size							(Return the number of students the school class has)
	
	// Public Setters
	public void addGradeLevel(GradeLevel gradeLevel);									// Method: addGradeLevel				(Add another grade level the school class has)
	public void addGroup(Group group);													// Method: addGroup						(Add another group the school class has))
	public void addStudent(Student student);											// Method: addStudent					(Add another student the school class has)
	
	// Public Methods
	public void resetGroups();															// Method: resetGroups					(Remove all groups)
	public ArrayList<Group> removeEmptyGroups();										// Method: removeEmptyGroups			(Remove groups which are not filled)
	public ArrayList<Group> createGroupTemplates(int minimumSize, int maximumSize);		// Method: createGroupTemplates 		(Automatically create groups based on the subjects that the students in the class follow)

} // End Interface: SchoolClassInterface
