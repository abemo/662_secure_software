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
        
    }

}