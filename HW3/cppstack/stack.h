#ifndef STACK_H
#define STACK_H

#include <memory>
#include <string>

#define MAX_STACK_SIZE 65536

enum class ResponseCode { // TODO should this class be nested within the STACK class?
  OutOfMemory,
  StackEmpty,
  Success
};

class StringResponse { // TODO should this class be nested within the STACK class?
public:
  StringResponse(const std::string& value, ResponseCode code);
  ~StringResponse();
  std::string value;
  ResponseCode code;
};

class Stack {
public:
  Stack(); // TODO should the constructor be private? And then have a StackResponse class that creates the stack?
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
