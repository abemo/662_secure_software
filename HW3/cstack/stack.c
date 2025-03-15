#include "stack.h"
#include <stdlib.h>
#include <string.h>

struct S {
  char* data[MAX_STACK_SIZE];
  int size;
};

StackResponse createStack() {
  Stack s = malloc(sizeof(struct S));
  if (s == NULL) {
    return (StackResponse) {NULL, out_of_memory};
  }
  s->size = 0;
  return (StackResponse) {s, success};
}

void destroyStack(Stack s) {
  for (int i = 0; i < MAX_STACK_SIZE; i++) {
    free(s->data[i]);
  }
  free(s);
}

response_code push(Stack s, char* value) {
  if (isFull(s)) {
    return stack_full;
  }
  s->data[s->size++] = strdup(value); 
  return success;
}

StringResponse peek(Stack s) {
  return isEmpty(s) 
    ? (StringResponse) {NULL, stack_empty} 
    : (StringResponse) {strdup(s->data[s->size - 1]), success};
}

StringResponse pop(Stack s) {
  if (isEmpty(s)) {
    return (StringResponse) {NULL, stack_empty};
  } else {
    const int top_index = s->size - 1;
    const char* top_value = s->data[top_index];
    s->data[top_index] = NULL;
    s->size--;
    return (StringResponse) {top_value, success};
  }
}

_Bool isEmpty(Stack s) {
  return s->size == 0;
}

_Bool isFull(Stack s) {
  return s->size == MAX_STACK_SIZE;
}

int size(Stack s) {
  return s->size;
}
