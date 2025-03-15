CC = gcc
CFLAGS = -std=c2x -Wall -Wextra -Wpedantic -Wshadow -Wconversion -Werror -g
LDFLAGS = 

SRC = stack.c test.c
OBJ = $(SRC:.c=.o)
TARGET = test

all: $(TARGET)

$(TARGET): $(OBJ)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJ) $(LDFLAGS)

%.o: %.c stack.h
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	rm -f $(OBJ) $(TARGET)

.PHONY: all clean
