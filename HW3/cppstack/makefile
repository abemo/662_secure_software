CC = g++
CFLAGS = -std=c++20 -Wall -Wextra -Wpedantic -Wshadow -Wconversion -Werror -g
LDFLAGS = 

SRC = stack.cpp test.cpp
OBJ = $(SRC:.cpp=.o)
TARGET = test

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJ) $(LDFLAGS)

%.o: %.cpp stack.h
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	rm -f $(OBJ) $(TARGET)

.PHONY: all clean
