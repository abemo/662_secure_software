#include <stdbool.h>

#define MAX_STACK_SIZE 32

typedef struct S* Stack;

typedef enum {
  out_of_memory,
  stack_full,
  stack_empty,
  invalid_argument,
  success
} response_code;

typedef struct {
  Stack stack;
  response_code code;
} StackResponse;

typedef struct {
  char* value;
  response_code code;
} StringResponse;

StackResponse createStack();
response_code push(Stack s, char* value);
StringResponse pop(Stack s);
StringResponse peek(Stack s);
void destroyStack(Stack s);
_Bool isEmpty(Stack s);
_Bool isFull(Stack s);
int size(Stack s);
int top(Stack s);
