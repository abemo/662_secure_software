#include "stack.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

struct S {
  char** data;
  int size;
  int top;
};

StackResponse createStack() {
  Stack s = malloc(sizeof(struct S));
  if (s == NULL) {
    return (StackResponse) {NULL, out_of_memory};
  }
  s->size = 16;
  s->top = -1;
  s->data = calloc(s->size, sizeof(char*) * s->size);
  if (s->data == NULL) {
    free(s);
    return (StackResponse) {NULL, out_of_memory};
  }
  return (StackResponse) {s, success};
}

response_code push(Stack s, char* value) {
  if (s == NULL || value == NULL) {
    return invalid_argument;
  }
  if (isFull(s)) {
    return stack_full;
  }
  if (s->top == s->size - 1) {
    int newSize = s->size * 2;
    if (newSize > MAX_STACK_SIZE) {
      newSize = MAX_STACK_SIZE;
    }
    char** temp = calloc(newSize, sizeof(char*) * newSize);
    if (temp == NULL) {
      return out_of_memory;
    }
    for (int i = 0; i < s->size; i++) {
      temp[i] = s->data[i];
    }
    free(s->data);
    s->data = temp;
    s->size = newSize;
  }
  s->data[++s->top] = strdup(value);
  return success;
}

StringResponse pop(Stack s) {
  if (s == NULL) {
    return (StringResponse){NULL, invalid_argument};
  }
  if (isEmpty(s)) {
    return (StringResponse) {NULL, stack_empty};
  } else {
    char* top_value = strdup(s->data[s->top--]);
    free(s->data[s->top + 1]);
    return (StringResponse) {top_value, success};
  }
}

StringResponse peek(Stack s) {
  if (s == NULL) {
    return (StringResponse){NULL, invalid_argument};
  }
  return isEmpty(s) 
    ? (StringResponse) {NULL, stack_empty}
    : (StringResponse) {strdup(s->data[s->top]), success};
}

void destroyStack(Stack s) {
  if (s == NULL)
    return;
  for (int i = 0; i <= s->top; i++) {
    free(s->data[i]);
  }
  free(s->data);
  free(s);
}

_Bool isEmpty(Stack s) {
  if (s == NULL) return 1;
  return s->top == -1;
}

_Bool isFull(Stack s) {
  if (s == NULL) return 0;
  return s->top >= MAX_STACK_SIZE - 1;
}

int size(Stack s) {
  if (s == NULL) return -1;
  return s->size;
}

int top(Stack s) {
  if (s == NULL) return -1;
  return s->top;
}
