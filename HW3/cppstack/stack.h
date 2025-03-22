#ifndef STACK_H
#define STACK_H

#include <memory>
#include <string>

#define STARTING_STACK_SIZE 128
#define MAX_STACK_SIZE 65536

enum class ResponseCode {
  OutOfMemory,
  StackEmpty,
  Success,
  StackFull
};

class StringResponse {
public:
  StringResponse(const std::string& value, ResponseCode code);
  ~StringResponse();
  std::string value;
  ResponseCode code;
};

class Stack {
public:
  Stack();
  ~Stack();

  ResponseCode push(const std::string& value);
  StringResponse peek() const;
  StringResponse pop();
  bool isEmpty() const;
  bool isFull() const;
  size_t size() const;

private:
  std::unique_ptr<std::string[]> stack;
  size_t top;
  size_t array_length;
};

#endif // STACK_H
