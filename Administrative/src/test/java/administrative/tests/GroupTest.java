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
import administrative.School;
import administrative.SchoolClass;
import administrative.Student;
import administrative.Subject;

// Import Test Unit Library
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;



// Public Test Unit Class
public class GroupTest extends TestCase {   // Start Test Case Class: GroupTest
	
	// Private Object Variables
	private School school;
	private Group group;
	private SchoolClass classNO16;
	private Student student;
	private ArrayList<Student> students;
	private Subject subjectMath;
	private Subject subjectHistory;
	private Subject subjectLanguage;
	
	
	
	// Setup - Resets with each test case method
	public void setUp() {   // Start Method: setUp
		school = new School("Linnéuniversitet");
		classNO16 = new SchoolClass(school, "NO16");
		students = new ArrayList<Student>();
		subjectMath = new Subject(school, "Math");
		subjectHistory = new Subject(school, "History");
		subjectLanguage = new Subject(school, "Language");
	} // End Method: setUp
	
	
	
	// Create the test case, @param testName name of the test case
	public GroupTest(String testName) {   // Start Constructor: GroupTest
		super(testName);
	} // End Constructor: GroupTest
	
	
	
	// @return the suite of tests being tested
	public static Test suite() {
		return new TestSuite(GroupTest.class);
	}
	
	
	
	// Test Cases begin here
	
	// Test Unit Case 1: Let two students join a group, while a third student is not suitable
	public void test_groupSize() {   // Start Test Unit Method: test_groupSize
		
		// Create 3 students and add them in a class
		Student student1 = new Student(school, "John Doe", "900101-1234");
		Student student2 = new Student(school, "John Doe", "900101-1234");
		Student student3 = new Student(school, "John Doe", "900101-1234");
		classNO16.addStudent(student1);
		classNO16.addStudent(student2);
		classNO16.addStudent(student3);
		
		// Register subjects for each students
		student1.registerSubject(subjectMath);						// Math and history for both students 1 and 2
		student1.registerSubject(subjectHistory);
		student2.registerSubject(subjectMath);
		student2.registerSubject(subjectHistory);
		student3.registerSubject(subjectMath);						// Only math for student 3
		
		// Create a new group, add it into a class, add subjects and fill it with students
		group = new Group(school, 1, 5);
		classNO16.addGroup(group);
		group.addSubject(subjectMath);
		group.addSubject(subjectHistory);
		group.populate();
		
		assertEquals(2, group.size());								// There are 2 students in the group
		assertEquals(group, student1.getGroup());					// The group of the first student is the same group that was just created
		assertEquals(group, student2.getGroup());					// The group of the second student is also the same group that was just created
		assertFalse(student3.hasGroup());							// The third student has no group
	} // End Test Unit Method: test_groupSize
	
	// Test Unit Case 2: If there are too many students suitable for a group, let none of them join the group
	public void test_tooLargeGroup() {   // Start Test Unit Method: test_tooLargeGroup
		
		// Create 110 new students each studying math and put them in a class
		for (int i=0; i<110; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			student.registerSubject(subjectMath);
			classNO16.addStudent(student);
			assertEquals(1, student.totalSubjects());				// Just making sure each student exists and studies math
			assertNotNull(student.getSchoolClass());				// Just making sure the student is part of a class
			students.add(student);
		}
		
		// Create a new group, add it into a class, add subjects and fill it with students
		group = new Group(school, 20, 100);
		classNO16.addGroup(group);
		group.addSubject(subjectMath);
		group.populate();
		
		assertEquals(110, students.size());							// There were just 110 students created
		assertEquals(0, group.size());								// None of those are in the group
		assertFalse(group.startStudy());							// The group can not start, since it has no students
		
	} // End Test Unit Method: test_tooLargeGroup
	
	// Test Unit Case 3: If there are too few students suitable for a group, let none of them join the group
	public void test_tooSmallGroup() {   // Start Test Unit Method: test_tooSmallGroup
		
		// Create 5 new students each studying math and put them in a class
		for (int i=0; i<5; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			student.registerSubject(subjectMath);
			classNO16.addStudent(student);
			assertEquals(1, student.totalSubjects());				// Just making sure each student exists and studies math
			assertNotNull(student.getSchoolClass());				// Just making sure the student is part of a class
			students.add(student);
		}
		
		// Create a new group, add it into a class, add subjects and fill it with students
		group = new Group(school, 20, 100);							// Minimum of 20 students, so 5 students are not enough
		classNO16.addGroup(group);
		group.addSubject(subjectMath);
		group.populate();
		
		assertEquals(5, students.size());							// There were just 5 students created
		assertEquals(0, group.size());								// None of those are in the group
		assertFalse(group.startStudy());							// The group can not start, since it has no students
		
	} // End Test Unit Method: test_tooSmallGroup
	
	// Test Unit Case 4: Report 10 students who were not able to join a group
	public void test_reportStudentWithoutGroup() {   // Start Test Unit Method: test_reportStudentsWithoutGroup
		
		// Create 10 new students each studying math and put them in a class
		for (int i=0; i<10; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			student.registerSubject(subjectMath);
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Create a new group, add it into a class, add subjects and fill it with students
		group = new Group(school, 20, 100);							// Minimum of 20 students, so 10 students are not enough
		classNO16.addGroup(group);
		group.addSubject(subjectMath);
		group.populate();
		
		assertTrue(school.hasUngroupedStudents());					// There should be students without a group
		assertEquals(10, school.numberOfUngroupedStudents());		// 10 students without a group
		
	} // End Test Unit Method: test_reportStudentsWithoutGroup
	
	// Test Unit Case 5: Students are assigned into two groups, make sure no students are reported to be left ungrouped
	public void test_twoGroups() {   // Start Test Unit Method: text_twoGroups
		
		// Create 20 new students and put them in a class
		for (int i=0; i<20; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 10)												// The first 10 students study math and the last 10 students study history
				student.registerSubject(subjectMath);
			else student.registerSubject(subjectHistory);
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Create a new group, add it into a class, add subjects and fill it with students
		Group groupA = new Group(school, 5, 20);
		classNO16.addGroup(groupA);
		groupA.addSubject(subjectMath);
		groupA.populate();
		
		// Create another new group, add it into a class, add subjects and fill it with students
		Group groupB = new Group(school, 5, 20);
		classNO16.addGroup(groupB);
		groupB.addSubject(subjectHistory);
		groupB.populate();
		
		assertFalse(school.hasUngroupedStudents());				// There are no ungrouped students
		assertEquals(0, school.numberOfUngroupedStudents());	// 20/20 students have a group
		assertNull(school.reportUngroupedStudents());			// There should be no list of ungrouped students
		
		assertEquals(10, groupA.size());						// 10 suitable students for group A
		assertEquals(10, groupB.size());						// 10 suitable students for group B
		
	} // End Test Unit Method: text_twoGroups
	
	// Test Unit Case 6: Let a 10 students join a group and fail another 10 students to join a group (and report those)
	public void test_mix() {   // Start Test Unit Method: text_mix
		
		// Create 20 new students and put them in a class
		for (int i=0; i<20; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 10)											// The first 10 students study math and the last 10 students study history
				student.registerSubject(subjectMath);
			else student.registerSubject(subjectHistory);
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Create a new group, add it into a class, add subjects and fill it with students
		Group groupA = new Group(school, 5, 20);
		classNO16.addGroup(groupA);
		groupA.addSubject(subjectMath);
		groupA.populate();
		
		// Create a second group, add it into a class, add subjects and fill it with students
		Group groupB = new Group(school, 15, 20);
		classNO16.addGroup(groupB);
		groupB.addSubject(subjectHistory);
		groupB.populate();
		
		// Create a third group, add it into a class, add subjects and fill it with students
		Group groupC = new Group(school, 15, 20);
		classNO16.addGroup(groupC);
		groupC.addSubject(subjectMath);
		groupC.populate();
		
		// Create a fourth group, add it into a class, add subjects and fill it with students
		Group groupD = new Group(school, 5, 20);
		classNO16.addGroup(groupD);
		groupD.addSubject(subjectMath);
		groupD.populate();
		
		// Create a fifth group, add it into a class, add subjects and fill it with students
		Group groupE = new Group(school, 5, 20);
		classNO16.addGroup(groupE);
		groupE.addSubject(subjectLanguage);
		groupE.populate();
		
		assertTrue(school.hasUngroupedStudents());				// There are ungrouped students
		assertEquals(10, school.numberOfUngroupedStudents());	// 10/20 students have no group
		assertNotNull(school.reportUngroupedStudents());		// There should be a list of ungrouped students
		
		assertEquals(0, groupE.size());							// No suitable students for group E
		assertEquals(10, groupA.size());						// Enough suitable students for group A
		assertEquals(0, groupB.size());							// Too few suitable students for group B
		assertEquals(0, groupC.size());							// Too few suitable students for group C
		assertEquals(0, groupD.size());							// Students already joined group D
		
	} // End Test Unit Method: text_mix
	
	// Test Unit Case 7: Create groups for all the subjects that the school has
	public void test_groupTemplates() {   // Start Test Unit Method: test_groupTemplates
		
		assertEquals(0, classNO16.getGroups().size());				// Just making sure there are no groups yet
		assertEquals(0, school.getGroups().size());					// Just making sure that the school has no groups yet
		assertEquals(3, school.getSubjects().size());				// There should be 3 subjects in total
		
		classNO16.createGroupTemplates(1, 10);						// Create groups for the missing subject combinations
		
		assertEquals(0, classNO16.getGroups().size());				// There should be 7 created now
		assertEquals(0, school.getGroups().size());					// The school should have 7 groups
		
		// Create 3 new students and put them in a class
		for (int i=0; i<70; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 10)
				student.registerSubject(subjectMath);
			else if (i < 20)
				student.registerSubject(subjectHistory);
			else if (i < 30)
				student.registerSubject(subjectLanguage);
			else if (i < 40) {
				student.registerSubject(subjectMath);
				student.registerSubject(subjectHistory);
			}
			else if (i < 50) {
				student.registerSubject(subjectMath);
				student.registerSubject(subjectLanguage);
			}
			else if (i < 60) {
				student.registerSubject(subjectHistory);
				student.registerSubject(subjectLanguage);
			}
			else {
				student.registerSubject(subjectMath);
				student.registerSubject(subjectHistory);
				student.registerSubject(subjectLanguage);
			}
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		classNO16.createGroupTemplates(1, 10);						// Create groups for the missing subject combinations
		
		assertEquals(7, classNO16.getGroups().size());				// There should be 7 created now
		assertEquals(7, school.getGroups().size());					// The school should have 7 groups
		
		// Check the amount of subjects each group has
		assertEquals(1, classNO16.getGroup(0).getSubjects().size());	// First three groups contains a single subject only
		assertEquals(1, classNO16.getGroup(1).getSubjects().size());
		assertEquals(1, classNO16.getGroup(2).getSubjects().size());
		assertEquals(2, classNO16.getGroup(3).getSubjects().size());	// Once all groups have an unique combination of a single subject, the next three groups contains two subjects only
		assertEquals(2, classNO16.getGroup(4).getSubjects().size());
		assertEquals(2, classNO16.getGroup(5).getSubjects().size());
		assertEquals(3, classNO16.getGroup(6).getSubjects().size());	// Once all groups have an unique combination of two subjects, the final group contains three subjects only*/
		
	} // End Test Unit Method: test_groupTemplates
	
	// Test Unit Case 8: Create groups for all the subjects that the school has and fill those with students
	public void test_groupTemplatesWithStudents() {   // Start Test Unit Method: test_groupTemplatesWithStudents
		
		assertEquals(0, classNO16.getGroups().size());				// Just making sure there are no groups yet
		assertEquals(0, school.getGroups().size());					// Just making sure that the school has no groups yet
		
		// Create 30 new students and put them in a class
		for (int i=0; i<30; i++) {
			student = new Student(school, "John Doe", "900101-1234");
			
			if (i < 15)												// 15 of them study math
				student.registerSubject(subjectMath);
			if (i >= 10 && i < 20)									// 10 of them study history
				student.registerSubject(subjectHistory);
			if (i >= 15)											// 15 of them study language
				student.registerSubject(subjectLanguage);
			
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Create all unique combinations of subjects for classes
		classNO16.createGroupTemplates(5, 10);
		assertEquals(4, classNO16.getGroups().size());				// Should result in 7 groups if using three subjects, but results in 4 groups when filling them with students
		
		// Try and fill each group with students
		for (Group group : classNO16.getGroups())
			group.populate();
		
		//The amount of students that should be following each subject
		assertEquals(15, subjectMath.getStudents().size());
		assertEquals(10, subjectHistory.getStudents().size());
		assertEquals(15, subjectLanguage.getStudents().size());
		
		assertEquals(10, classNO16.getGroup(0).getStudents().size());	// Math only:					10 students
		assertEquals(10, classNO16.getGroup(1).getStudents().size());	// Language only:				10 students
		assertEquals(5, classNO16.getGroup(2).getStudents().size());	// Math + History:				5 students
		assertEquals(5, classNO16.getGroup(3).getStudents().size());	// History + Language:			5 students
		
		assertEquals(0, school.numberOfUngroupedStudents());		// There should be a suitable group for each student
		assertFalse(school.hasUngroupedStudents());					// There should not be any students without a group
		
	} // End Test Unit Method: test_groupTemplatesWithStudents
	
	// Test Unit Case 9: Create groups for all the subjects that the school has and fill those with students, but fail to do so
	public void test_manualGroupCreation() {   // Start Test Unit Method: test_manualGroupCreation
		
		// Create 50 new students and put them in a class
		for (int i=0; i<50; i++) {
			student = new Student(school, "John Doe", "900101-1234");
					
			if (i < 25)												// 25 of them study math
				student.registerSubject(subjectMath);
			else student.registerSubject(subjectHistory);			// 25 of them study history
					
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Create all unique combinations of subjects for classes
		classNO16.createGroupTemplates(5, 10);						// These group limits are obviously too small
		
		// Try and fill each group with students
		for (Group group : classNO16.getGroups())
			group.populate();
		
		assertEquals(0, classNO16.getGroups().size());				// Should result in 3 groups if using two subjects, but results in 0 groups since they could not be filled with students
		assertEquals(50, school.numberOfUngroupedStudents());		// The groups are too small to contain 25 students, so 50 students are without a group
		assertTrue(school.hasUngroupedStudents());					// All students are ungrouped
		
		// Create two groups manually which should fit the size of the students
		group = new Group(school, 15, 30);
		group.addSubject(subjectMath);
		classNO16.addGroup(group);
		group.populate();
		assertEquals(25, group.size());								// The first group has 25 students
		
		group = new Group(school, 15, 30);
		group.addSubject(subjectHistory);
		classNO16.addGroup(group);
		group.populate();
		assertEquals(25, group.size());								// The second group has 25 students
		
		assertEquals(0, school.numberOfUngroupedStudents());		// There should no longer by any students without a group
		assertFalse(school.hasUngroupedStudents());					// All students are now grouped
		
	} // End Test Unit Method: test_manualGroupCreation
	
	// Test Unit Case 10: Create groups for all the subjects that the school has and fill those with students, but fail to do so resulting in another attempt
	public void test_redoGroupTemplates() {   // Start Test Unit Method: test_manualGroupCreation
		
		// Create 50 new students and put them in a class
		for (int i=0; i<50; i++) {
			student = new Student(school, "John Doe", "900101-1234");
					
			if (i < 25)												// 25 of them study math
				student.registerSubject(subjectMath);
			else student.registerSubject(subjectHistory);			// 25 of them study history
					
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Create all unique combinations of subjects for classes
		classNO16.createGroupTemplates(5, 10);						// These group limits are obviously too small
		assertEquals(0, classNO16.getGroups().size());				// Should result in 3 groups if using two subjects
		assertEquals(0, school.getGroups().size());					// Should result in 3 groups if using two subjects
		
		// Try and fill each group with students
		for (Group group : classNO16.getGroups())
			group.populate();
			
		assertEquals(50, school.numberOfUngroupedStudents());		// The groups are too small to contain 25 students, so 50 students are without a group
		assertTrue(school.hasUngroupedStudents());					// All students are ungrouped
			
		// Create all unique combinations of subjects for classes, this time with different group size limits
		classNO16.resetGroups();
		classNO16.createGroupTemplates(15, 30);						// These group limits are obviously too small
		assertEquals(2, classNO16.getGroups().size());				// Should result in 3 groups if using two subjects, but results in 2 groups when filled with students
		assertEquals(2, school.getGroups().size());					// The school should still have 3 groups (all 3 removed, and added again), but results in 2 groups when filled with students
		
		// Try and fill each group with students again
		for (Group group : classNO16.getGroups())
			group.populate();
			
		assertEquals(0, school.numberOfUngroupedStudents());		// There should no longer by any students without a group
		assertFalse(school.hasUngroupedStudents());					// All students are now grouped
		
	} // End Test Unit Method: test_manualGroupCreation
	
	// Test Unit Case 11: Create one huge group for a massive amount of students
	public void test_hugeGroupTemplate() {   // Start Test Unit Method: test_hugeGroupTemplate
		
		// Create 5000 new students that study math and history and put them in a class
		for (int i=0; i<5000; i++) {
			student = new Student(school, "John Doe", "900101-1234");		
			student.registerSubject(subjectMath);
			student.registerSubject(subjectHistory);		
			classNO16.addStudent(student);
			students.add(student);
		}
		
		// Create all unique combinations of subjects for classes, as long there are sufficient many suitable students for it
		classNO16.createGroupTemplates(1000, 10000);
		
		// There should be 1 group, with 5000 students in the group
		assertEquals(1, classNO16.getGroups().size());
		assertEquals(1, school.getGroups().size());
		assertEquals(5000, classNO16.getGroup(0).size());
		assertEquals(5000, school.getGroup(0).size());
		
		assertEquals(0, school.numberOfUngroupedStudents());		// There are no students without a group
		assertFalse(school.hasUngroupedStudents());					// All students are grouped
		
	} // End Test Unit Method: test_hugeGroupTemplate
	
	// Test Unit Case 12: Verify a group is empty
		public void test_emptyGroup() {   // Start Test Unit Method: test_emptyGroup
			
			// Create a new group and verify it is empty
			group = new Group(school, 3, 5);
			assertTrue(group.isEmpty());							// Is empty
			
			// Add 3 students studying math
			for (int i=0; i<3; i++) {
				student = new Student(school, "John Doe", "900101-1234");		
				student.registerSubject(subjectMath);	
				classNO16.addStudent(student);
				students.add(student);
			}
			
			// Try to populate the group and verify it is empty
			group.populate();
			assertTrue(group.isEmpty());							// Is empty
			
			// Let's try that again, now add a subject to the group, try to populate it again and verify if it is empty
			group.addSubject(subjectMath);
			group.populate();
			assertTrue(group.isEmpty());							// Is empty
			
			// Now add the group to a class, try to populate it again and verify if it is still empty
			classNO16.addGroup(group);
			group.populate();
			assertFalse(group.isEmpty());							// Is no longer empty
			
		} // End Test Unit Method: test_emptyGroup
	
} // End Test Case Class: GroupTest