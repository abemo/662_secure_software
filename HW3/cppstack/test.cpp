#include "stack.h"
#include <assert.h>
#include <stdio.h>
#include <string.h>

int main() {
  Stack s;
  assert(s.isEmpty());
  assert(!s.isFull());
  assert(s.size() == 0);

  StringResponse pr = s.peek();
  assert(pr.code == ResponseCode::StackEmpty);

  ResponseCode rc = s.push("hello");
  assert(rc == ResponseCode::Success);
  assert(!s.isEmpty());
  assert(!s.isFull());
  assert(s.size() == 1);

  pr = s.peek();
  assert(pr.code == ResponseCode::Success);
  assert(pr.value == "hello");

  rc = s.push("foo");
  assert(rc == ResponseCode::Success);
  rc = s.push("bar");
  assert(rc == ResponseCode::Success);
  rc = s.push("baz");
  assert(rc == ResponseCode::Success);
  assert(!s.isEmpty());
  assert(s.size() == 4);

  pr = s.peek();
  assert(pr.code == ResponseCode::Success);
  assert(pr.value == "baz");

  for (int i = 5; i <= MAX_STACK_SIZE; i++) {
    rc = s.push("junk");
    assert(rc == ResponseCode::Success);
  }
  assert(s.isFull());
  assert(s.size() == MAX_STACK_SIZE);

  rc = s.push("overflow");
  assert(rc == ResponseCode::StackFull);

  pr = s.pop();
  assert(pr.code == ResponseCode::Success);
  assert(pr.value == "junk");
  assert(s.size() == MAX_STACK_SIZE - 1);

  for (int i = MAX_STACK_SIZE - 1; i > 0; i--) {
    pr = s.pop();
    assert(pr.code == ResponseCode::Success);
  }
  assert(s.isEmpty());
  assert(s.size() == 0);

  pr = s.pop();
  assert(pr.code == ResponseCode::StackEmpty);

  puts("All tests passed");
  return 0;
}
