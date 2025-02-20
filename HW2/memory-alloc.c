/*
MEM35-C. Allocate sufficient memory for an object
https://wiki.sei.cmu.edu/confluence/display/c/MEM35-C.+Allocate+sufficient+memory+for+an+object

The types of integer expressions used as size arguments to malloc(), calloc(), realloc(), or
aligned_alloc() must have sufficient range to represent the size of the objects to be stored.
If size arguments are incorrect or can be manipulated by an attacker, then a buffer overflow may
occur. Incorrect size arguments, inadequate range checking, integer overflow, or truncation can
result in the allocation of an inadequately sized buffer.
*/
#include <stdio.h>
// #include <string.h>
#include <stdlib.h>

struct Student {
    int grade;
    int idNumber;
    char *name;
};

// this is an example of non-compliant code
struct Student *nonCompliantStudent(int grade, int idNumber, char *name) {
    struct Student *student;
    student = (struct Student *)malloc(sizeof(student));

    if (student == NULL) {
        return NULL;
    }

    *student = (struct Student) {
        .grade = grade, .idNumber = idNumber, .name = name
    };

    return student;
}

// this is an example of compliant code
struct Student *compliantStudent(int grade, int idNumber, char *name) {
    struct Student *student;
    student = (struct Student *)malloc(sizeof(*student));

    if (student == NULL) {
        return NULL;
    }

    *student = (struct Student) {
        .grade = grade, .idNumber = idNumber, .name = name
    };

    return student;
}

int main(void) {
    struct Student *s = nonCompliantStudent(4, 50, "Abe");
    if (s != NULL) {
        printf("%s\n", s->name);
    }

    s = compliantStudent(4, 51, "CJ");
    if (s != NULL) {
        printf("%s\n", s->name);
    }
}