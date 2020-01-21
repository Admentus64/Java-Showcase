/*
 * Author: Robert Willem Hallink
 * Created: 28 November, 2019
 * Java, Maven Project
 *  Open JDK 13.0.1, Eclipse 2019-09 4.13.0, Default Settings
 */

// Package
package administrative;



// Import Java Library
import java.util.ArrayList;

// Imports
import administrative.GradeLevel;
import administrative.Group;
import administrative.School;
import administrative.Student;
import administrative.simple.SchoolClassInterface;



// Public Class
public class SchoolClass implements SchoolClassInterface {   // Start Class: SchoolClass
	
	// Private General Variables
	private SchoolClass self = this;
	private School school;
	
	// Private Variables
	private String name;
	
	// Private Object Variables
	private ArrayList<GradeLevel> gradeLevels = new ArrayList<GradeLevel>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Student> students = new ArrayList<Student>();
	
	
	
	// Constructor
	public SchoolClass(School school, String name) {   // Start Constructor: SchoolClass
		for (SchoolClass schoolClass : school.getClasses())
			if (schoolClass.getName() == name) {
				self = null;
				throw new IllegalArgumentException("Name for class is already in use.");
			}
		
		self.school = school;
		school.addClass(self);
		self.name = name;
	} // End Constructor: SchoolClass
	
	
	
	// Public Getters
	public School getSchool()							{ return school; }						// Method: getSchool		(Return the school the school class is part of, single object)
	public String getName()								{ return name; }						// Method: getName			(Return the name of the school class)
	public ArrayList<GradeLevel> getGradeLevels()		{ return gradeLevels; }					// Method: getGradeLevels	(Return all grade levels the school class has, multiple objects)
	public GradeLevel getGradeLevel(int i)				{ return gradeLevels.get(i); }			// Method: getGradeLevel	(Return a specific grade level the school class has, single object)
	public ArrayList<Group> getGroups()					{ return groups; }						// Method: getGroups		(Return all groups the school class has, multiple objects)
	public Group getGroup(int i)						{ return groups.get(i); }				// Method: getGroup			(Return a group the school class has, single object)
	public ArrayList<Student> getStudents()				{ return students; }					// Method: getStudents		(Return all students the school class has, multiple objects)
	public Student getStudent(int i)					{ return students.get(i); }				// Method: getStudent		(Return a specific student the school class has, single object)
	public int size()									{ return students.size(); }				// Method: size				(Return the number of students the school class has)
	
	
	
	// Public Setters
	// Add another grade level the school class has
	public void addGradeLevel(GradeLevel gradeLevel) {   // Start Method: addGradeLevel
		
		if (!gradeLevels.contains(gradeLevel))
			gradeLevels.add(gradeLevel);
		
	} // End Method: addGradeLevel
	
	// Add another group the school class has
	public void addGroup(Group group) {   // Start Method: addGroup
		
		if (!groups.contains(group)) {
			groups.add(group);
			group.addToClass(self);
		}
			
	} // End Method: addGroup	
	
	// Add another student the school class has
	public void addStudent(Student student) {   // Start Method: addStudent
		
		if (!students.contains(student)) {
			students.add(student);
			student.addToClass(self);
		}
		
	} // End Method: addStudent
	
	
	
	// Public Methods
	// Remove all groups
	public void resetGroups() {   // Start Method: resetGroups
		
		for (Group group : groups)
			school.removeGroup(group);
		groups = new ArrayList<Group>();
		
	} // End Method: resetGroups
	
	// Remove groups which are not filled
	public ArrayList<Group> removeEmptyGroups() {   // Start Method: removeEmptyGroups
		
		ArrayList<Group> newGroups = new ArrayList<Group>();
		
		// Go through each group, make a copy of it when it has students and remove it from the school if it is empty
		for (Group group : groups) {
			if (group.isEmpty())
				school.removeGroup(group);
			if (!group.isEmpty())
				newGroups.add(group);
		}
		
		return groups = newGroups;
		
	} // End Method: removeEmptyGroups
	
	// Automatically create and populate groups based on the subjects that the students in the class follow
	public ArrayList<Group> createGroupTemplates(int minimumSize, int maximumSize) {   // Start Method: createGroupTemplates
		
		ArrayList<Subject> subjects = new ArrayList<Subject>();
		Group newGroup;
		
		// Start by removing unused groups
		removeEmptyGroups();
		
		// Only create groups with subjects which are actually being sought after by students
		for (Subject subject : school.getSubjects())
			for (Student student : students)
				if (student.getSubjects().contains(subject)) {
					subjects.add(subject);
					break;
				}
		
		// Create a new group for each subject
		if (subjects.size() >= 1)
			for (Subject subject : subjects) {
				boolean ignore = false;
				
				for (Group existingGroup : groups)
					if (existingGroup.getSubjects().contains(subject)) {
						ignore = true;
						break;
					}
				
				if (!ignore) {
					newGroup = new Group(school, minimumSize, maximumSize);
					newGroup.addSubject(subject);
					addGroup(newGroup);
					//System.out.println(group.getSubject(0).getName());
				}
			}
		
		// Create a new group for each unique set of 2 different subjects
		if (subjects.size() >= 2)
			for (Subject subject1 : subjects)
				for (Subject subject2 : subjects)
					if (subject1 != subject2) {
						boolean ignore = false;
						
						for (Group existingGroup : groups)
							if (existingGroup.getSubjects().contains(subject1) && existingGroup.getSubjects().contains(subject2)) {
								ignore = true;
								break;
							}
						
						if (!ignore) {
							newGroup = new Group(school, minimumSize, maximumSize);
							newGroup.addSubject(subject1);
							newGroup.addSubject(subject2);
							addGroup(newGroup);
							//System.out.println(newGroup.getSubject(0).getName() + " " + newGroup.getSubject(1).getName());
						}
					}
		
		// Create even more groups if there are at least 3 subjects
		if (subjects.size() >= 3)
			for (Subject subject1 : subjects)
				for (Subject subject2 : subjects)
					for (Subject subject3 : subjects)
						if (subject1 != subject2 && subject1 != subject3 && subject2 != subject3) {
							boolean ignore = false;
							
							for (Group existingGroup : groups)
								if (existingGroup.getSubjects().contains(subject1) && existingGroup.getSubjects().contains(subject2) && existingGroup.getSubjects().contains(subject3)) {
									ignore = true;
									break;
								}
							
							if (!ignore) {
								newGroup = new Group(school, minimumSize, maximumSize);
								newGroup.addSubject(subject1);
								newGroup.addSubject(subject2);
								newGroup.addSubject(subject3);
								addGroup(newGroup);
								//System.out.println(newGroup.getSubject(0).getName() + " " + newGroup.getSubject(1).getName() + " " + newGroup.getSubject(2).getName());
							}
						}
		
		// Try and fill each group with students
		for (Group group : groups)
			group.populate();
		
		// Remove groups which are empty
		removeEmptyGroups();
		
		// Return all groups for reference
		if (subjects.size() == 0)
			return null;
		return groups;
		
	} // End Method: createGroupTemplates
	
} // End Class: SchoolClass
