package hw_1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void testValidItemCreation() {
        assertDoesNotThrow(() -> new Item("ValidItem", 99.99));
        assertDoesNotThrow(() -> new Item("Item123", 500));
        assertDoesNotThrow(() -> new Item("Another Item", 0));
    }

    @Test
    void testInvalidItemName_NullOrEmpty() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new Item(null, 10.0));
        assertEquals("Item name cannot be null or empty", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new Item("", 10.0));
        assertEquals("Item name cannot be null or empty", exception2.getMessage());
    }

    @Test
    void testInvalidItemName_NonAlphanumeric() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Item("Invalid@Name", 10.0));
        assertEquals("Item name must be alphanumeric", exception.getMessage());
    }

    @Test
    void testInvalidItemName_LengthConstraints() {
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new Item("AB", 10.0));
        assertEquals("Item name invalid length", exception1.getMessage());

        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new Item("A".repeat(51), 10.0));
        assertEquals("Item name invalid length", exception2.getMessage());
    }

    @Test
    void testInvalidPrice_Negative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Item("ValidItem", -1.0));
        assertEquals("Item price cannot be negative", exception.getMessage());
    }

    @Test
    void testInvalidPrice_ExceedsMaximum() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Item("ValidItem", 1000.01));
        assertEquals("Item price exceeds maximum allowed", exception.getMessage());
    }
}
