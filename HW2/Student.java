/*
 * MET01-J. Never use assertions to validate method arguments
 * 
 * Never use assertions to validate arguments of public methods.
 * The Java Language Specification, §14.10, "The assert Statement" [JLS 2015], states that
 * assertions should not be used for argument checking in public methods. Argument checking
 * is typically part of the contract of a method, and this contract must be upheld whether
 * assertions are enabled or disabled.
 * 
 * A secondary problem with using assertions for argument checking is that erroneous arguments
 * should result in an appropriate run-time exception (such as IllegalArgumentException,
 * IndexOutOfBoundsException, or NullPointerException). An assertion failure will not throw an
 * appropriate exception.
 */

package HW2;

public class Student {
    public int studentGrade;
    public int studentID;
    public String studentName;
    
    // This is an example of compliant code
    public Student(int studentGrade, int studentID, String studentName) {
        if (studentGrade <= 0 || studentGrade > 12) {
            throw new IllegalArgumentException("Grade must be between 1 and 12");
        }

        if (studentID <= 0) {
            throw new IllegalArgumentException("ID number must be positive");
        }

        if (studentName.length() <= 0) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        this.studentGrade = studentGrade;
        this.studentID = studentID;
        this.studentName = studentName;
    }

    // This is an example of non-compliant code
    public static Student NonCompliantStudent(int studentGrade, int studentID, String studentName) {
        assert studentGrade > 0 && studentGrade <= 12;
        assert studentID > 0;
        assert studentName.length() > 0;

        return new Student(studentGrade, studentID, studentName);
    }
}