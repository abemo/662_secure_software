public class Stack {
  private static final int MAX_CAPACITY = 65536;
  private String[] elements;
  private int size;

  public Stack() {
    elements = new String[MAX_CAPACITY];
    size = 0;
  }

  public boolean isFull() {
    return size == MAX_CAPACITY;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public void push(String item) {
    if (isFull()) {
      throw new IllegalStateException("Stack is full");
    }
    elements[size++] = item;
  }

  public String pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return elements[--size];
  }

  public String peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return elements[size - 1];
  }
}
