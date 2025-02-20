/*
MSC50-CPP. Do not use std::rand() for generating pseudorandom numbers
https://wiki.sei.cmu.edu/confluence/display/cplusplus/MSC50-CPP.+Do+not+use+std%3A%3Arand%28%29+for+generating+pseudorandom+numbers 

The std::rand() function is not suitable for generating pseudorandom numbers, as the numbers it generates can be easily predicted.
This program demonstrates safe and unsafe random number generation.
*/

#include <iostream>
#include <random>

// this is an example of poor random number generation
// additionally, because std::rand is not being seeded, it is not guaranteed to produce different results each time
void unsafeRoll(int numberDice, int numberSides) {
  std::cout << "Unsafe roll\n";
  for (int i = 0; i < numberDice; ++i) {
    int result = std::rand() % numberSides + 1;
    std::cout << result << "\n";
  }
}

// this is an example of good pseudo-random number generation
void safeRoll(int numberDice, int numberSides) {
  if (numberDice <= 0 || numberSides <= 0) {
    std::cerr << "Invalid input\n";
    return;
  }
  std::cout << "Safe roll\n";
  std::uniform_int_distribution<int> dist(1, numberSides);
  std::random_device rd;
  std::mt19937 gen(rd());
  for (int i = 0; i < numberDice; ++i) {
    int result = dist(gen);
    std::cout << result << "\n";
  }
}

int main() {
  int numberDice = 2;
  int numberSides = 6;
  unsafeRoll(numberDice, numberSides);
  safeRoll(numberDice, numberSides);
}