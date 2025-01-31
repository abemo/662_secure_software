package hw_1;

import static org.junit.jupiter.api.Assertions.assertEquals; // TODO remove

import org.junit.jupiter.api.Test;

public class ShoppingCartTests {
    @Test
    public void testDefault() { // TODO remove
        assertEquals("hi", "hi");
    }

    @Test
    public void newCartIsEmpty() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        assertEquals(0, cart.items().size());
    }

    @Test
    public void CustomerIdCannotChange() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        String customerId = cart.customerId();
        customerId = "ABC12345DE-B";
        assertEquals("ABC12345DE-A", cart.customerId());
    }

    @Test
    public void CustomerIdMustBeValid() {
        try {
            new ShoppingCart(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Customer ID cannot be null", e.getMessage());
        }
        try {
            new ShoppingCart("");
        } catch (IllegalArgumentException e) {
            assertEquals("Customer ID cannot be empty", e.getMessage());
        }
        try {
            new ShoppingCart("ABC12345DE-1");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid customer ID format", e.getMessage());
        }
    }

    @Test
    public void addItem() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        cart.addItem("apple", 5);
        assertEquals(5, cart.items().get("apple"));
    }

    @Test
    public void validateItemName() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("$$$", 99);
            System.err.println("\n\n\n\n\n\n\ngottem");
        } catch (IllegalArgumentException e) {
            assertEquals("Item names must only contain letters, numbers, and spaces.", e.getMessage());
        }
        try {
            cart.addItem("0123456789 0123456789 0123456789 0123456789 0123456789", 99);
            System.err.println("\n\n\n\n\n\n\ngottem");
        } catch (IllegalArgumentException e) {
            assertEquals("Item name is too long", e.getMessage());
        }
        try {
            cart.addItem("", 99);
            System.err.println("\n\n\n\n\n\n\ngottem");
        } catch (IllegalArgumentException e) {
            assertEquals("Item name cannot be empty", e.getMessage());
        }
        try {
            cart.addItem("skibidi toilet", 69);
        } catch (IllegalArgumentException e) {
            assertEquals("Item is not in the catalog", e.getMessage());
        }
    }

    public void validateQuantity() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
        try {
            cart.addItem("apple", 101);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
        try {
            cart.addItem("apple", 100);
            cart.addItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
        try {
            cart.addItem("apple", 1);
            cart.removeItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
        try {
            cart.addItem("apple", 1);
            cart.removeItem("apple", 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
        try {
            cart.removeItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Item does not exist in cart", e.getMessage());
        }
        try {
            cart.addItem("apple", 1);
            cart.removeItem("apple", 2);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity exceeds the quantity of the item in the cart", e.getMessage());
        }
    }
}