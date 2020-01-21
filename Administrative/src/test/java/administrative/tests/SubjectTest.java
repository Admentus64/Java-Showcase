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
import administrative.Group;
import administrative.MandatorySubject;
import administrative.School;
import administrative.SchoolClass;
import administrative.Student;
import administrative.Subject;

// Import Test Unit Library
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



//Public Test Unit Class
public class SubjectTest extends TestCase {   // Start Test Case Class: SubjectTest
	
	// Private Object Variables
	private School school;
	private Group groupA;
	private Group groupB;
	private SchoolClass classNO16;
	private Student student;
	private ArrayList<Student> students;
	private Subject subjectMath;
	private Subject subjectHistory;
	private Subject subjectLanguage;
	
	
	
	// Setup - Resets with each test case method
	public void setUp() {   // Start Method: setUp
		school = new School("Linnéuniversitet");
		groupA = new Group(school, 5, 20);
		groupB = new Group(school, 5, 20);
		students = new ArrayList<Student>();
		subjectMath = new MandatorySubject(school, "Math");
		subjectHistory = new Subject(school, "History");
		subjectLanguage = new Subject(school, "Language");
		
		classNO16 = new SchoolClass(school, "NO16");
		classNO16.addGroup(groupA);
		classNO16.addGroup(groupB);
	}
	
	
	
	// Create the test case, @param testName name of the test case
	public SubjectTest(String testName) {   // Start Constructor: SubjectTest
		super(testName);
	} // End Constructor: SubjectTest
	
	
	
	// @return the suite of tests being tested
	public static Test suite() {
		return new TestSuite(SubjectTest.class);
	}
	
	
	
	// Test Cases begin here
	
	// Test Unit Case 1: Let 5 students join a group with suitable subjects
	public void test_addIntoOneGroup() {   // Start Test Unit Method: test_addIntoOneGroup
		
		// Create 5 new students studying both math and history and put them in a class
		for (int i=0; i<5; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			student.registerSubject(subjectMath);
			student.registerSubject(subjectHistory);
			classNO16.addStudent(student);
			
			assertEquals(subjectMath, student.getSubject(0));		// The first subject of each student is math
			assertEquals(subjectHistory, student.getSubject(1));	// The second subject of each student is history
			
			// Each student has two subjects, where the first subject is named Math and the second subject is named History
			assertEquals("Math", student.getSubject(0).getName());
			assertEquals("History", student.getSubject(1).getName());
			assertEquals(2, student.getSubjects().size());
		}
		
		// Add subjects to both groups and try and fill both groups with students
		groupA.addSubject(subjectMath);
		groupA.addSubject(subjectHistory);
		groupA.populate();
		
		assertEquals(5, subjectMath.getStudents().size());			// There are 5 students studying math
		assertEquals(5, subjectHistory.getStudents().size());		// There are 5 students studying history
		
	} // End Test Unit Method: test_addIntoOneGroup
	
	// Test Unit Case 2: Let 15 students split into 2 groups with suitable subjects
	public void test_addIntoTwoGroups1() {   // Start Test Unit Method: test_addIntoTwoGroups1
		
		// Create 15 new students and put them in a class
		for (int i=0; i<15; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 10) {											// 10 of them study both math and history
				student.registerSubject(subjectMath);
				student.registerSubject(subjectHistory);
			}
			else student.registerSubject(subjectLanguage);			// 5 of them study language
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Add subjects to both groups and try and fill both groups with students
		groupA.addSubject(subjectMath);								// Group A studies both math and history
		groupA.addSubject(subjectHistory);
		groupB.addSubject(subjectLanguage);							// Group B studies language only
		
		groupA.populate();
		groupB.populate();
		
		assertEquals(10, groupA.size());							// 10 students in group A
		assertEquals(5, groupB.size());								// 5 students in group B
		
	} // End Test Unit Method: test_addIntoTwoGroups1
	
	// Test Unit Case 3: Let 15 students split into 2 groups with suitable subjects
	public void tests_addIntoTwoGroups2() {   // Start Test Unit Method: tests_addIntoTwoGroups2
		
		// Create 15 new students and put them in a class
		for (int i=0; i<15; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 10)												// 10 of them study math
				student.registerSubject(subjectMath);
			if (i >= 5 && i < 10)									// 5 of them study history, but also math
				student.registerSubject(subjectHistory);
			if (i >= 10)											// 5 of them study language
				student.registerSubject(subjectLanguage);
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Add subjects to both groups and try and fill both groups with students
		groupA.addSubject(subjectMath);								// Group A studies both math and history
		groupA.addSubject(subjectHistory);
		groupB.addSubject(subjectLanguage);							// Group B studies language only
		
		groupA.populate();
		groupB.populate();
		
		assertEquals(5, groupA.size());								// Only 5 students should study both math and history
		assertEquals(5, groupB.size());								// Only 5 students should study language
		
	} // End Test Unit Method: tests_addIntoTwoGroups2
	
	// Test Unit Case 4: Let 15 students split into 2 groups with suitable subjects
	public void tests_addIntoTwoGroups3() {   // Start Test Unit Method: tests_addIntoTwoGroups3
		
		// Create 15 new students and put them in a class
		for (int i=0; i<15; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 10) {											// 10 of them study both math and history
				student.registerSubject(subjectMath);
				student.registerSubject(subjectHistory);
			}
			else {													// 5 of them study both math and language
				student.registerSubject(subjectMath);
				student.registerSubject(subjectLanguage);
			}
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Add subjects to both groups and try and fill both groups with students
		groupA.addSubject(subjectMath);								// Group A studies both math and history
		groupA.addSubject(subjectHistory);
		groupB.addSubject(subjectMath);								// Group B studies both math and language
		groupB.addSubject(subjectLanguage);
		
		groupA.populate();
		groupB.populate();
		
		assertEquals(10, groupA.size());							// Only 10 students should study both math and history
		assertEquals(5, groupB.size());								// Only 5 students should study both math and language
		
	} // End Test Unit Method: tests_addIntoTwoGroups3
	
	// Test Unit Case 5: Have one group partially fill with students and leave another group empty, considering suitable subjects
	public void test_fillOnlyOneGroup1() {   // Start Test Unit Method: test_fillOnlyOneGroup1
		
		for (int i=0; i<15; i++) {									// 15 new students
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 10)												// 10 of them study math, while 5 of them also study history
				student.registerSubject(subjectMath);
			if (i >= 5 && i < 15)									// 10 of them study history, while 5 of them also study math
				student.registerSubject(subjectHistory);
			if (i >= 10 && i < 15)									// 5 of them study language, while also history
				student.registerSubject(subjectLanguage);
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Add subjects to both groups and try and fill both groups with students
		groupA.addSubject(subjectMath);								// Group A studies both math and history
		groupA.addSubject(subjectHistory);
		groupB.addSubject(subjectLanguage);							// Group B studies language only
		
		groupA.populate();
		groupB.populate();
		
		assertEquals(5, groupA.size());								// Only 5 students study both math and history
		assertEquals(0, groupB.size());								// No students exclusively study language
		
	} // End Test Unit Method: test_fillOnlyOneGroup1
	
	// Test Unit Case 6: Have one group partially fill with students and leave another group empty, considering suitable subjects
	public void test_fillOnlyOneGroup2() {   // Start Test Unit Method: test_fillOnlyOneGroup2
		
		// Create 30 new students and put them in a class
		for (int i=0; i<30; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 15)												// 15 of them study math
				student.registerSubject(subjectMath);
			if (i >= 5)												// 25 of them study history
				student.registerSubject(subjectHistory);
			if (i >= 10)											// 20 of them study language
				student.registerSubject(subjectLanguage);
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Add subjects to both groups and try and fill both groups with students
		groupA.addSubject(subjectMath);								// Group A studies both math and history
		groupA.addSubject(subjectHistory);
		groupB.addSubject(subjectLanguage);							// Group B studies language only
		
		groupA.populate();
		groupB.populate();
		
		assertEquals(5, groupA.size());								// Only 5 students study both math and history
		assertEquals(0, groupB.size());
		
	} // End Test Unit Method: test_fillOnlyOneGroup2
	
	// Test Unit Case 7: Have students register for the same subject twice
	public void test_duplicateSubjects() {   // Start Test Unit Method: test_duplicateSubjects
		
		// Create 10 new students
		for (int i=0; i<10; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			student.registerSubject(subjectMath);
			student.registerSubject(subjectMath);
			student.registerSubject(subjectHistory);
			classNO16.addStudent(student);
			
			assertEquals(2, student.totalSubjects());				// Each student has two subjects, not three (maths twice)
			assertEquals(subjectMath, student.getSubject(0));		// The first subject is math
			assertEquals(subjectHistory, student.getSubject(1));	// The second subject is history
			
			students.add(student);
		}
		
		assertEquals(10, students.size());							// There are 10 students
		
		// Since students are not assigned to a group yet which is valid, both courses should not be running yet
		assertEquals(0, subjectMath.getStudents().size());			// There are no students running the math course
		assertEquals(0, subjectHistory.getStudents().size());		// There are no students running the history course
		
		// Add subjects to both groups and try and fill both groups with students
		groupA.addSubject(subjectMath);								// Group A studies both math and history
		groupA.addSubject(subjectHistory);
		groupA.populate();
		
		// Now that groups are made and students are assigned, the courses are active as well
		assertEquals(10, subjectMath.getStudents().size());			// There are 10 students studying math
		assertEquals(10, subjectHistory.getStudents().size());		// There are 10 students studying history
		
	} // End Test Unit Method: test_duplicateSubjects
	
	// Test Unit Case 8: Create a mandatory subject course
	public void test_isMandatory() {   // Start Test Unit Method: test_isMandatory
		
		assertFalse(subjectHistory.isMandatory());					// Regular subject, not mandatory
		assertTrue(subjectMath.isMandatory());						// Mandatory subject
		
	} // End Test Unit Method: test_isMandatory
	
} // End Test Case Class: SubjectTest