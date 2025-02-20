/*
MSC52-CPP. Value-returning functions must return a value from all exit paths
https://wiki.sei.cmu.edu/confluence/display/cplusplus/MSC52-CPP.+Value-returning+functions+must+return+a+value+from+all+exit+paths

A value-returning function must return a value from all code paths; otherwise, it will result in undefined behavior.
This includes returning through less-common code paths, such as from a function-try-block, as explained in the
C++ Standard, [except.handle], paragraph 15: 
    Flowing off the end of a function-try-block is equivalent to a return
    with no value; this results in undefined behavior in a value-returning
    function (6.6.3).
*/
#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;

struct Student {
    int grade;
    int idNumber;
    string name;

    Student(int studentGrade, int studentID, string studentName) {
        if (studentGrade < 1 || studentGrade > 12) {
            throw invalid_argument("Grade must be between 1 and 12");
        }

        if (studentID <= 0) {
            throw invalid_argument("ID number must be positive");
        }

        if (studentName.length() == 0) {
            throw invalid_argument("Name cannot be empty");
        }

        grade = studentGrade;
        idNumber = studentID;
        name = studentName;
    }
};

// this is an example of non-compliant code
Student nonCompliantStudent(int grade, int idNumber, string name) try {
    Student student = {grade, idNumber, name};
    return student;
} catch (invalid_argument e) {
    cerr << "Error creating student instance: " << e.what() << "\n";
}

// this is an example of compliant code
Student compliantStudent(int grade, int idNumber, string name) try {
    Student student = {grade, idNumber, name};
    return student;
} catch (invalid_argument e) {
    cerr << "Error creating student instance: " << e.what() << "\n";
    exit(1);
}

int main(void) {
    compliantStudent(3, 1, "");
}