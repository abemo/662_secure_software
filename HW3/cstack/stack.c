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
  s->data = malloc(sizeof(char*) * s->size);
  if (s->data == NULL) {
    free(s);
    return (StackResponse) {NULL, out_of_memory};
  }

  return (StackResponse) {s, success};
}

response_code push(Stack s, char* value) {
  if (isFull(s)) {
    return stack_full;
  }
  if(s->top == s->size - 1) {
    char** temp = malloc(sizeof(char*) * s->size * 2);
    if (temp == NULL) {
      return out_of_memory;
    } else {
      int i = 0;
      for (i = 0; i < s->size; i++) {
        temp[i] = s->data[i];
      }
      free(s->data);
      s->data = temp;
      s->size *= 2;
    }
  }
  s->data[++s->top] = strdup(value); 
  return success;
}

StringResponse pop(Stack s) {
  if (isEmpty(s)) {
    return (StringResponse) {NULL, stack_empty};
  } else {
    const char* top_value = s->data[s->top--];
    s->data[s->top + 1] = NULL;
    return (StringResponse) {top_value, success};
  }
}

StringResponse peek(Stack s) {
  return isEmpty(s) 
    ? (StringResponse) {NULL, stack_empty}
    : (StringResponse) {strdup(s->data[s->top]), success};
}

void destroyStack(Stack s) {
  for (int i = 0; i <= s->top; i++) {
    free(s->data[i]);
  }
  free(s->data);
  free(s);
}

_Bool isEmpty(Stack s) {
  return s->top == -1;
}

_Bool isFull(Stack s) {
  return s->top >= MAX_STACK_SIZE;
}

_Bool isFullyExpanded(Stack s) {
  return s->size >= MAX_STACK_SIZE;
}

int size(Stack s) {
  return s->size;
}

int top(Stack s) {
  return s->top;
}
