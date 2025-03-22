import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StackTests {
    private Stack stack;

    @BeforeEach
    public void setUp() {
        stack = new Stack();
    }

    @Test
    public void testStackIsEmptyOnCreation() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPopEmptyStack() {
        assertTrue(stack.isEmpty());
        Throwable exception = assertThrows(IllegalStateException.class,
                () -> stack.pop());
        assertEquals("Stack is empty", exception.getMessage());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPeekEmptyStack() {
        assertTrue(stack.isEmpty());
        Throwable exception = assertThrows(IllegalStateException.class,
                () -> stack.peek());
        assertEquals("Stack is empty", exception.getMessage());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPush_t0() {
        assertTrue(stack.isEmpty());
        stack.push("hello");
        assertEquals(1, stack.size());
        assertEquals("hello", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testPush_t1() {
        assertTrue(stack.isEmpty());
        stack.push("foo");
        stack.push("bar");
        stack.push("baz");
        assertEquals(3, stack.size());
        assertFalse(stack.isEmpty());
        assertEquals("baz", stack.pop());
        assertEquals("bar", stack.peek());
        assertEquals(2, stack.size());
        assertEquals("bar", stack.pop());
        assertEquals("foo", stack.pop());
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testExpand() {
        assertTrue(stack.isEmpty());
        for (int i = 0; i < 130; i++) {
            stack.push("junk");
            assertEquals(i + 1, stack.size());
        }
        assertFalse(stack.isFull());
        assertEquals("junk", stack.peek());
    }

    @Test
    public void testMaxCapacity() {
        assertTrue(stack.isEmpty());
        for (int i = 0; i < 65536; i++) {
            stack.push("junk");
            assertEquals(i + 1, stack.size());
        }
        Throwable exception = assertThrows(IllegalStateException.class,
                () -> stack.push("hello"));
        assertEquals("Stack is full", exception.getMessage());
        assertEquals("junk", stack.peek());
        assertTrue(stack.isFull());
    }
}