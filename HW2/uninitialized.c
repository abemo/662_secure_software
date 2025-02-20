/*
EXP33-C. Do not read uninitialized memory
https://wiki.sei.cmu.edu/confluence/display/c/EXP33-C.+Do+not+read+uninitialized+memory

In C you must allocate memory and initialize it before reading from it.
Reading uninitialized memory can result in undefined behavior.  Don't do that.
*/

#include <stdio.h>
#include <stdlib.h>

// this is an example of a program that reads uninitialized memory
void readUninitializedMemory() {
  int* ptr;
  printf("%d\n", *ptr);
}

// this is an example of a compliant program that reads initialized memory
void readInitializedMemory() {
  int* ptr = (int*)malloc(sizeof(int));
  *ptr = 5;
  printf("%d\n", *ptr);
  free(ptr);
}

int main() {
    readUninitializedMemory();
    readInitializedMemory();
}