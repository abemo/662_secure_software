#include "stack.h"
#include <assert.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
  puts("Starting tests...");
  int testNum = 0;

  /* =========================================================================
     Basic Functionality Tests
     ========================================================================= */
  printf("Test %d: Create stack (size should be 16, top -1) ... ", ++testNum);
  StackResponse sr = createStack();
  assert(sr.code == success);
  Stack s = sr.stack;
  assert(isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 16);
  assert(top(s) == -1);
  printf("OK\n");

  printf("Test %d: Peek or pop from empty stack should return stack_empty ... ", ++testNum);
  StringResponse pr = peek(s);
  assert(pr.code == stack_empty);
  pr = pop(s);
  assert(pr.code == stack_empty);
  printf("OK\n");

  printf("Test %d: Pushing a value should succeed ... ", ++testNum);
  response_code rc = push(s, "hello");
  assert(rc == success);
  assert(!isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 16);
  assert(top(s) == 0);
  printf("OK\n");

  printf("Test %d: Peeking returns a defensive copy of the top value ... ", ++testNum);
  pr = peek(s);
  assert(pr.code == success);
  pr.value[0] = 'H';  // Modify copy
  StringResponse pr2 = peek(s);
  assert(pr2.code == success);
  assert(strcmp(pr2.value, "hello") == 0);
  free(pr.value);
  free(pr2.value);
  printf("OK\n");

  printf("Test %d: Popping returns a defensive copy of the top value ... ", ++testNum);
  rc = push(s, "world");
  assert(rc == success);
  pr = pop(s);
  assert(pr.code == success);
  pr.value[0] = 'W';  // Modify copy
  free(pr.value);
  printf("OK\n");

  printf("Test %d: Pushing multiple values ... ", ++testNum);
  rc = push(s, "foo");
  assert(rc == success);
  rc = push(s, "bar");
  assert(rc == success);
  rc = push(s, "baz");
  assert(rc == success);
  assert(top(s) == 3);
  printf("OK\n");

  printf("Test %d: Popping multiple values returns them in reverse order ... ", ++testNum);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "baz") == 0);
  free(pr.value);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "bar") == 0);
  free(pr.value);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "foo") == 0);
  free(pr.value);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "hello") == 0);
  free(pr.value);
  assert(isEmpty(s));
  assert(top(s) == -1);
  printf("OK\n");

  /* =========================================================================
     Stack Expansion Test
     ========================================================================= */
  printf("Test %d: Pushing value to trigger expansion (size should become 32) ... ", ++testNum);
  for (int j = 0; j <= 16; j++) {
    rc = push(s, "junk");
    assert(rc == success);
  }
  assert(!isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 32);
  assert(top(s) == 16);
  printf("OK\n");

  printf("Test %d: Pushing up to MAX_STACK_SIZE, then one extra should return stack_full ... ", ++testNum);
  for (int j = top(s) + 1; j < MAX_STACK_SIZE; j++) {
    rc = push(s, "junk");
    assert(rc == success);
  }
  rc = push(s, "overflow");
  assert(rc == stack_full);
  assert(!isEmpty(s));
  assert(isFull(s));
  assert(size(s) == MAX_STACK_SIZE);
  printf("OK\n");

  printf("Test %d: Popping all values returns them in reverse order ... ", ++testNum);
  for (int j = MAX_STACK_SIZE; j > 0; j--) {
    assert(j == top(s) + 1);
    pr = pop(s);
    assert(pr.code == success);
    assert(strcmp(pr.value, "junk") == 0);
    free(pr.value);
  }
  assert(isEmpty(s));
  assert(size(s) == 32);
  assert(top(s) == -1);
  printf("OK\n");

  /* =========================================================================
     Argument Validation Tests
     ========================================================================= */
  printf("Test %d: Pushing NULL should return invalid_argument ... ", ++testNum);
  rc = push(s, NULL);
  assert(rc == invalid_argument);
  printf("OK\n");

  printf("Test %d: Pushing on a NULL stack should return invalid_argument ... ", ++testNum);
  rc = push(NULL, "test");
  assert(rc == invalid_argument);
  printf("OK\n");

  printf("Test %d: Popping from a NULL stack should return invalid_argument ... ", ++testNum);
  pr = pop(NULL);
  assert(pr.code == invalid_argument);
  printf("OK\n");

  printf("Test %d: Peeking into a NULL stack should return invalid_argument ... ", ++testNum);
  pr = peek(NULL);
  assert(pr.code == invalid_argument);
  printf("OK\n");

  printf("Test %d: Destroying a NULL stack should not crash ... ", ++testNum);
  destroyStack(NULL);
  printf("OK\n");

  /* =========================================================================
     Sequential Operations Test
     ========================================================================= */
  printf("Test %d: Multiple sequential push and pop operations ... ", ++testNum);
  sr = createStack();
  assert(sr.code == success);
  s = sr.stack;
  rc = push(s, "alpha");
  assert(rc == success);
  rc = push(s, "beta");
  assert(rc == success);
  
  pr = peek(s);
  assert(pr.code == success);
  char* firstPeek = pr.value;
  
  pr = pop(s);
  assert(pr.code == success);
  
  pr = peek(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "alpha") == 0);
  
  free(firstPeek);
  free(pr.value);
  printf("OK\n");

  /* Cleanup */
  destroyStack(s);

  puts("\nAll tests passed successfully.");
  return 0;
}
