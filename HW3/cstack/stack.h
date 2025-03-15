// so far this just defines a NONexapandable stack of strings
#include <stdbool.h>

#define MAX_STACK_SIZE 65536

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
void destroyStack(Stack s);
response_code push(Stack s, char* value);
StringResponse peek(Stack s);
StringResponse pop(Stack s);
_Bool isEmpty(Stack s);
_Bool isFull(Stack s);
int size(Stack s);
