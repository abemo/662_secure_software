#include "stack.h"
#include <assert.h>
#include <stdio.h>
#include <string.h>

int main() {
  StackResponse sr = createStack();
  assert(sr.code == success);
  Stack s = sr.stack;
  assert(isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 0);
  StringResponse pr = peek(s);
  assert(pr.code == stack_empty);
  response_code rc = push(s, "hello");
  assert(rc == success);
  assert(!isEmpty(s));
  assert(!isFull(s));
  assert(size(s) == 1);
  pr = peek(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "hello") == 0);
  rc = push(s, "foo");
  assert(rc == success);
  rc = push(s, "bar");
  assert(rc == success);
  rc = push(s, "baz");
  assert(rc == success);
  assert(!isEmpty(s));
  assert(size(s) == 4);
  pr = peek(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "baz") == 0);
  for (int i = 5; i <= MAX_STACK_SIZE; i++) {
    rc = push(s, "junk");
    assert(rc == success);
  }
  assert(isFull(s));
  assert(size(s) == MAX_STACK_SIZE);
  rc = push(s, "overflow");
  assert(rc == stack_full);
  pr = pop(s);
  assert(pr.code == success);
  assert(strcmp(pr.value, "junk") == 0);
  assert(size(s) == MAX_STACK_SIZE - 1);
  for (int i = MAX_STACK_SIZE - 1; i > 0; i--) {
    pr = pop(s);
    assert(pr.code == success);
  }
  assert(isEmpty(s));
  assert(size(s) == 0);
  pr = pop(s);
  assert(pr.code == stack_empty);
  destroyStack(s);
  puts("All tests passed");
  return 0;
}
