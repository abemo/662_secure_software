#include "stack.h"
#include <memory>
#include <string>

StringResponse::StringResponse(const std::string& value, ResponseCode code)
  : value(value), code(code) {}

StringResponse::~StringResponse() {}

Stack::Stack() : stack(std::make_unique<std::string[]>(STARTING_STACK_SIZE)), top(0), array_length(STARTING_STACK_SIZE) {}

Stack::~Stack() {}

ResponseCode Stack::push(const std::string& value) {
  if (top == MAX_STACK_SIZE) {
    return ResponseCode::StackFull;
  }

  if (isFull()) {
    // array_length += STARTING_STACK_SIZE;
    array_length = array_length * 2;
    if (array_length > MAX_STACK_SIZE) {
      return ResponseCode::OutOfMemory;
    }
    std::unique_ptr<std::string[]> resized_stack = std::make_unique<std::string[]>(array_length);
    for (size_t i = 0; i < top; i++) {
      resized_stack[i] = stack[i];
    }

    stack.swap(resized_stack);
  }
  stack[top++] = value;
  return ResponseCode::Success;
}

StringResponse Stack::peek() const {
  if (isEmpty()) {
    return StringResponse("", ResponseCode::StackEmpty);
  }
  return StringResponse(stack[top - 1], ResponseCode::Success);
}

StringResponse Stack::pop() {
  if (isEmpty()) {
    return StringResponse("", ResponseCode::StackEmpty);
  }
  
  std::string value = stack[--top];

  if (top > STARTING_STACK_SIZE && top == array_length / 4) {
    array_length /= 2;
    std::unique_ptr<std::string[]> resized_stack = std::make_unique<std::string[]>(array_length);
    for (size_t i = 0; i < top; i++) {
      resized_stack[i] = stack[i];
    }
    stack.swap(resized_stack);
  }

  return StringResponse(value, ResponseCode::Success);
}

bool Stack::isEmpty() const {
  return top == 0;
}

bool Stack::isFull() const {
  return top == array_length;
}

size_t Stack::size() const {
  return top;
}
