/*
 * Author: Robert Willem Hallink
 * Created: 28 November, 2019
 * Java, Maven Project
 * JUnit 3 as part of Open JDK 13.0.1, Eclipse 2019-09 4.13.0, Default Settings
 */



// Package
package administrative.tests;



// Import Java Library
import java.util.ArrayList;

// Imports
import administrative.GradeLevel;
import administrative.School;
import administrative.SchoolClass;
import administrative.Student;

// Import Test Unit Library
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



// Public Test Unit Class
public class SchoolClassTest extends TestCase {   // Start Test Case Class: SchoolClassTest
	
	// Private Object Variables
	private School school;
	private GradeLevel gradeLevel;
	private SchoolClass schoolClass;
	
	
	
	// Setup - Resets with each test case method
	public void setUp() {   // Start Method: setUp
		school = new School("Linnéuniversitet");
	}
	
	
	
	// Create the test case, @param testName name of the test case
	public SchoolClassTest(String testName) {   // Start Constructor: SchoolClassTest
		super(testName);
	} // End Constructor: SchoolClassTest
	
	
	
	// @return the suite of tests being tested
	public static Test suite() {
		return new TestSuite(SchoolClassTest.class);
	}
	
	
	
	// Test Cases begin here
	
	// Test Unit Case 1: Have one school class with multiple grade levels
	public void test_multipleGradesPerClass() {   // Start Test Unit Method: test_multipleGradeLevelPerClass
		
		// Create a new class
		schoolClass = new SchoolClass(school, "NO16");
		
		// Create 5 new grades for the class, each grade being a level higher
		for (int i=1; i<=5; i++)
			schoolClass.addGradeLevel(new GradeLevel(school, i));
		
		assertEquals(5, schoolClass.getGradeLevels().size());		// There should be 5 grade levels for the school class
		
		// Go through all grade levels within the school class to confirm their grade values
		assertEquals(1, schoolClass.getGradeLevel(0).getGrade());
		assertEquals(2, schoolClass.getGradeLevel(1).getGrade());
		assertEquals(3, schoolClass.getGradeLevel(2).getGrade());
		assertEquals(4, schoolClass.getGradeLevel(3).getGrade());
		assertEquals(5, schoolClass.getGradeLevel(4).getGrade());
		
	} // End Test Unit Method: test_multipleGradeLevelPerClass
	
	// Test Unit Case 2: Have multiple school classes with one grade level
	public void test_multipleClassesPerGrade() {   // Start Test Unit Method: test_multipleClassesPerGradeLevel
		
		// Create a new grade with five classes
		gradeLevel = new GradeLevel(school, 1);
		
		gradeLevel.addClass(new SchoolClass(school, "1A"));
		gradeLevel.addClass(new SchoolClass(school, "1B"));
		gradeLevel.addClass(new SchoolClass(school, "2A"));
		gradeLevel.addClass(new SchoolClass(school, "NO15"));
		gradeLevel.addClass(new SchoolClass(school, "NO16"));
		
		assertEquals(5, gradeLevel.getClasses().size());			// There should be 5 school classes for the grade level
		
		// Go through all school classes within the grade level to confirm their class names
		assertEquals("1A", gradeLevel.getClass(0).getName());
		assertEquals("1B", gradeLevel.getClass(1).getName());
		assertEquals("2A", gradeLevel.getClass(2).getName());
		assertEquals("NO15", gradeLevel.getClass(3).getName());
		assertEquals("NO16", gradeLevel.getClass(4).getName());
		
	} // End Test Unit Method: test_multipleClassesPerGradeLevel
	
	// Test Unit Case 3: Create a school class and a level grade and link them together
	public void test_linkClassAndGrade() {   // Start Test Unit Method: test_linkClassAndGrade
		
		schoolClass = new SchoolClass(school, "NO16");
		gradeLevel = new GradeLevel(school, 1);
		
		schoolClass.addGradeLevel(gradeLevel);
		gradeLevel.addClass(schoolClass);
		
		assertEquals(1, schoolClass.getGradeLevels().size());		// The school class should have one grade level
		assertEquals(1, gradeLevel.getClasses().size());			// The grade level should have one school class
		
	} // End Test Unit Method: test_linkClassAndGrade
	
	// Test Unit Case 4: Create 3 school classes and level grades and link them together
	public void test_multipleClassesAndGrades() {   // Start Test Unit Method: test_multipleClassesAndGrades
		
		// Create 3 new classes
		SchoolClass class1A = new SchoolClass(school, "1A");
		SchoolClass class1B = new SchoolClass(school, "1B");
		SchoolClass class2A = new SchoolClass(school, "2A");
		
		// Create 3 new grades and link those to the classes
		ArrayList<GradeLevel> gradeLevels = new ArrayList<GradeLevel>();
		for (int i=0; i<3; i++) {
			GradeLevel gradeLevel = new GradeLevel(school, i+1);
			
			gradeLevel.addClass(class1A);
			gradeLevel.addClass(class1B);
			gradeLevel.addClass(class2A);
			
			// Assign the new grade to each class
			class1A.addGradeLevel(gradeLevel);
			class1B.addGradeLevel(gradeLevel);
			class2A.addGradeLevel(gradeLevel);
			
			gradeLevels.add(gradeLevel);
		}
		
		// Each grade level should have 3 school classes, also quickly check if the class name matches
		assertEquals(3, gradeLevels.get(0).getClasses().size());
		assertEquals(3, gradeLevels.get(1).getClasses().size());
		assertEquals(3, gradeLevels.get(2).getClasses().size());
		assertEquals("1A", gradeLevels.get(0).getClass(0).getName());
		assertEquals("1B", gradeLevels.get(1).getClass(1).getName());
		assertEquals("2A", gradeLevels.get(2).getClass(2).getName());
		
		// Each school class should have 3 grade levels, also quickly check if the grade value matches
		assertEquals(3, class1A.getGradeLevels().size());
		assertEquals(3, class1B.getGradeLevels().size());
		assertEquals(3, class2A.getGradeLevels().size());
		assertEquals(1, class1A.getGradeLevel(0).getGrade());
		assertEquals(2, class1A.getGradeLevel(1).getGrade());
		assertEquals(3, class1A.getGradeLevel(2).getGrade());
		
	} // End Test Unit Method: test_multipleClassesAndGrades
	
	// Test Unit Case 5: Add 5 students to a school class
	public void test_addStudentsToClass() {   // Start Test Unit Method: test_addStudentsToClass
		
		// Create a new class with 3 grades
		schoolClass = new SchoolClass(school, "NO16");
		schoolClass.addGradeLevel(new GradeLevel(school, 1));
		schoolClass.addGradeLevel(new GradeLevel(school, 2));
		schoolClass.addGradeLevel(new GradeLevel(school, 3));
		
		// Add 5 students into a school class
		for (int i=0; i<5; i++) {
			Student student = new Student(school, "John Doe", "900101-1234");
			schoolClass.addStudent(student);
			assertEquals("NO16", student.getSchoolClass().getName());	// Check if the students themselves are linked to the school class
		}
		
		assertEquals(5, schoolClass.size());							// The school class should contain 5 students
		
	} // End Test Unit Method: test_addStudentsToClass
	
	// Test Unit Case 6: Have duplicate class names led to an error
	public void test_duplicateClassname() {   // Start Test Unit Method: test_duplicateClassname
		
		assertEquals(0, school.getClasses().size());				// Just to make sure there are no classes yet
		
		// Try and create 4 classes
		schoolClass = new SchoolClass(school, "NO16");
		schoolClass = new SchoolClass(school, "1A");
		
		// Try and make a second class with the same name, which should result in a fail
		try	{
			schoolClass = new SchoolClass(school, "NO16");
		}
		catch (IllegalArgumentException e) {
			assertEquals("Name for class is already in use.", e.getMessage());
		}
		
		assertEquals("1A", schoolClass.getName());					// Make sure the failed class did not get created, so the last successful schoolClass object was 1A.
		schoolClass = new SchoolClass(school, "2B");
		
		assertEquals(3, school.getClasses().size());				// There should be 3 classes, not 4
		assertEquals("NO16", school.getClass(0).getName());			// First class is NO16
		assertEquals("1A", school.getClass(1).getName());			// Second class is 1A
		assertEquals("2B", school.getClass(2).getName());			// Third class is 2B
		
	} // End Test Unit Method: test_duplicateClassname
	
} // End Test Case Class: SchoolClassTest