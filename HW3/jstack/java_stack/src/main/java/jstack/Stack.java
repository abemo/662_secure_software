public class Stack {
    private static final int MAX_CAPACITY = 65536;
    private static final int STARTING_STACK_SIZE = 128;
    private String[] elements;
    private int size;

    public Stack() {
        elements = new String[STARTING_STACK_SIZE];
        size = 0;
    }

    public boolean isFull() {
        return size == elements.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void push(String item) {
        if (size == MAX_CAPACITY) {
            throw new IllegalStateException("Stack is full");
        } else if (isFull()) {
            final String[] resizedStack = new String[elements.length * 2];
            for (int i = 0; i < elements.length; i++) {
                resizedStack[i] = elements[i];
            }
            elements = resizedStack;
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
