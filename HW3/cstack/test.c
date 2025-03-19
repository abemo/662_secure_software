#include "stack.h"
#include <assert.h>
#include <stdio.h>
#include <string.h>

int main() {
  puts("starting tests");
  int i = 0;

  printf("%d createStack should return a stack with size 16 and top -1\n", ++i);
  StackResponse sr = createStack();
  assert(sr.code == success);
  Stack s = sr.stack;
  assert(isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 16);
  assert(top(s) == -1);

  printf("%d peeking or popping an empty stack should return stack_empty\n", ++i);
  StringResponse pr = peek(s);
  assert(pr.code == stack_empty);
  pr = pop(s);
  assert(pr.code == stack_empty);

  printf("%d pushing a value should return success\n", ++i);
  response_code rc = push(s, "hello");
  assert(rc == success);
  assert(!isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 16);
  assert(top(s) == 0);

  printf("%d peeking should return the top value\n", ++i);
  pr = peek(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "hello") == 0);

  printf("%d popping should return the top value\n", ++i);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "hello") == 0);
  assert(isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 16);
  assert(top(s) == -1);

  printf("%d pushing 3 values should return success\n", ++i);
  rc = push(s, "foo");
  assert(rc == success);
  rc = push(s, "bar");
  assert(rc == success);
  rc = push(s, "baz");
  assert(rc == success);
  assert(top(s) == 2);

  printf("%d popping 3 values should return the values in reverse order\n", ++i);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "baz") == 0);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "bar") == 0);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "foo") == 0);
  assert(isEmpty(s));
  assert(top(s) == -1);

  printf("%d pushing a 16 value should expand the stack size to 32\n", ++i);
  for (int i = 0; i <= 16; i++) {
    rc = push(s, "JUNK");
    assert(rc == success);
  }
  assert(!isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 32);
  assert(top(s) == 16);

  printf("%d pushing a MAX_STACK_SIZE values should return stack_full\n", ++i);
  for (int i = 16; i <= MAX_STACK_SIZE; i++) {
    rc = push(s, "JUNK");
    assert(rc == success);
  } 
  rc = push(s, "overflow");
  assert(rc == stack_full);
  assert(!isEmpty(s));
  assert(isFull(s));
  assert(size(s) == MAX_STACK_SIZE);

  printf("%d popping all values should return the values in reverse order\n", ++i);
  for (int i = MAX_STACK_SIZE - 1; i > 0; i--) {
    pr = pop(s);
    assert(pr.code == success);
    assert(strcmp(pr.value, "junk") == 0);
    assert(i == top(s) - 1);
  }
  assert(isEmpty(s));
  assert(size(s) == 32);
  assert(top(s) == -1);

  printf("%d destroying the stack should free all memory\n", ++i);
  destroyStack(s);

  puts("All tests passed");
  return 0;
}
