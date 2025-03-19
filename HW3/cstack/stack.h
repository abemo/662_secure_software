// so far this just defines a NONexapandable stack of strings
#include <stdbool.h>

#define MAX_STACK_SIZE 32

typedef struct S* Stack;

typedef enum {
  out_of_memory,
  stack_full,
  stack_empty,
  success
} response_code;

typedef struct {
  Stack stack;
  response_code code;
} StackResponse;

typedef struct {
  const char* value;
  response_code code;
} StringResponse;

StackResponse createStack();
response_code push(Stack s, char* value);
StringResponse pop(Stack s);
StringResponse peek(Stack s);
void destroyStack(Stack s);
_Bool isEmpty(Stack s);
_Bool isFull(Stack s);
_Bool isFullyExpanded(Stack s);
int size(Stack s);
int top(Stack s);
