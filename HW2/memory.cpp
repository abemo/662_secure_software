/*
MEM50-CPP. Do not access freed memory
https://wiki.sei.cmu.edu/confluence/display/cplusplus/MEM50-CPP.+Do+not+access+freed+memory

When you free memory using delete, the memory is deallocated and the pointer is no longer valid. 
Accessing memory through a pointer that has been deleted results in undefined behavior. 
Don't do that.
*/

#include <iostream>

// this is an example of a program that accesses memory that has been freed
void accessFreedMemory() {
  int* ptr = new int(5);
  delete ptr;
  std::cout << *ptr << "\n";
}


// this is an example of a compliant program that accesses valid memory
void accessValidMemory() {
  int* ptr = new int(5);
  std::cout << *ptr << "\n";
  delete ptr;
}

int main() {
    accessFreedMemory();
    accessValidMemory();
}

