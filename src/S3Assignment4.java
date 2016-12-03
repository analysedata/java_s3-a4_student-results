/* Session 3, Assignment 4
 * Created by Mark Daniels on 12/2/16.
 * This program demonstrates object oriented concepts:
 * 	object, class, method, inheritance, overloading,
 *  overriding, abstraction, and also casting, looping,
 *  Scanner, decision statements, and types. 
 */

import java.util.Scanner;

public class S3Assignment4 {
	public static void main(String[] args) {
		// Scanner for accepting input
		Scanner inputScanner = new Scanner(System.in);
		int regNo = 0;
		int rollNo = 0;
		String studentName = null;
		double studentMarks = 0.0d;
		String collegeTerm = null;

		// Prompt user for number of students and hold in variable
		System.out.println("How many students?");
		int studentCount = Integer.parseInt(inputScanner.nextLine());
		
		// Prompt user for each student's details
		for (int i = 0; i < studentCount; i++) {

			System.out.println("\nStudent's registration number?");
			regNo = Integer.parseInt(inputScanner.nextLine());

			System.out.println("Student's roll number?");
			rollNo = Integer.parseInt(inputScanner.nextLine());

			System.out.println("Student's name?");
			studentName = inputScanner.nextLine();

			System.out.println("Student's marks?");
			studentMarks = Double.parseDouble(inputScanner.nextLine());

			System.out.println("Which term: Semester, Trimester, or Adhoc?");
			collegeTerm = inputScanner.nextLine();

			// Determine the term the student is doing and create the
			// object to process student information and print to screen
			if (collegeTerm.equals("Semester")) {

				// Prompt user for semester student information
				System.out.println("What subject must the marks be allocated to?");
				String subject = inputScanner.nextLine();
				
				System.out.println("Credits received?");
				int subjectCredit = Integer.parseInt(inputScanner.nextLine());
				
				SemesterStudent semesterStudent = new SemesterStudent(rollNo, studentName, regNo, collegeTerm);
				semesterStudent.markToGrade(studentMarks);
				semesterStudent.display(subject, subjectCredit);

			} else if (collegeTerm.equals("Trimester")) {

				// Prompt user for trimester student information
				TrimesterStudent trimesterStudent = new TrimesterStudent(rollNo, studentName, regNo, collegeTerm);
				trimesterStudent.markToGrade(studentMarks);
				trimesterStudent.display();
				
			} else {
				
				AdhocStudents adhocStudents = new AdhocStudents(rollNo, studentName, regNo, collegeTerm);
				adhocStudents.markToGrade(studentMarks);
				adhocStudents.display();
				
			}
		}

		// Close Scanner
		if (inputScanner != null) {
			System.out.println("\nEnd of program ...");
			inputScanner.close();
		}
	}
}

// Student abstract class
abstract class Student {
	// Data member declarations
	int roll;
	String name;
	int reg;
	float grade;
	String term;

	// Classes inheriting from the Student class must implement a markToGrade
	// method
	abstract void markToGrade(double mark);

	// Constructor
	public Student(int rollNo, String studentName, int regNo, String collegeTerm) {
		roll = rollNo;
		name = studentName;
		reg = regNo;
		term = collegeTerm;
	}

	// This method can be overloaded and overridden by descendant classes
	public void display() {
		System.out.println("\n" + name + "'s Details:\nRegistration number: " + reg + "\nRoll number: " + roll
				+ "\nName: " + name + "\nGrade: " + grade + "%");
	}
}

// SemesterStudent inherits from Student
class SemesterStudent extends Student {
	// Constructor
	public SemesterStudent(int rollNo, String studentName, int regNo, String collegeTerm) {
		super(rollNo, studentName, regNo, collegeTerm);
	}

	// Implementation of abstract class markToGrade
	public void markToGrade(double mark) {
		grade = (float) (mark * 1.1) / 100 * 100;
	}

	// Overload display method to include subject and credits
	public void display(String subject, int subjectCredit) {
		super.display();
		System.out.println("Subject: " + subject + "\nCredits: " + subjectCredit + "\nTerm: " + term);
	}
}

// TrimesterStudent inherits from Student
class TrimesterStudent extends Student {
	public TrimesterStudent(int rollNo, String studentName, int regNo, String collegeTerm) {
		super(rollNo, studentName, regNo, collegeTerm);
	}

	// Implementation of abstract class markToGrade
	public void markToGrade(double mark) {
		grade = (float) (mark * 1.2) / 100 * 100;
	}

	@Override
	public void display() {
		super.display();
		System.out.println("Term: " + term);
	}
}

// AdhocStudents inherits from Student
class AdhocStudents extends Student {
	public AdhocStudents(int rollNo, String studentName, int regNo, String collegeTerm) {
		super(rollNo, studentName, regNo, collegeTerm);
	}

	// Implementation of abstract class markToGrade
	public void markToGrade(double mark) {
		grade = (float) mark / 100 * 100;
	}
}
