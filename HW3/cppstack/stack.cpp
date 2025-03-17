// TODO rule of 5, do we need a copy constructor, copy assignment operator, a move constructor and a move assignment operator?

#include "stack.h"
#include <memory>
#include <string>

StringResponse::StringResponse(const std::string& value, ResponseCode code)
  : value(value), code(code) {}

StringResponse::~StringResponse() {}

Stack::Stack() : stack(std::make_unique<std::string[]>(MAX_STACK_SIZE)), top(0) {}

Stack::~Stack() {}

ResponseCode Stack::push(const std::string& value) {
  if (isFull()) {
    return ResponseCode::StackFull;
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
  return StringResponse(stack[--top], ResponseCode::Success);
}

bool Stack::isEmpty() const {
  return top == 0;
}

bool Stack::isFull() const {
  return top == MAX_STACK_SIZE;
}

size_t Stack::size() const {
  return top;
}
