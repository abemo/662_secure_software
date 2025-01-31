package hw_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ShoppingCartTests {
    @Test
    public void newCartIsEmpty() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        assertEquals(0, cart.items().size());
    }

    @Test
    public void validCustomerId() {
        ShoppingCart cart = new ShoppingCart("XHQ81739JK-Q");
        assertEquals(0, cart.items().size());
    }

    @Test
    public void customerIdCannotChange() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        String customerId = cart.customerId();
        customerId = "ABC12345DE-B";
        assertEquals("ABC12345DE-A", cart.customerId());
    }

    @Test
    public void customerIdCannotBeNull() {
        try {
            new ShoppingCart(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Customer ID cannot be null", e.getMessage());
        }
    }

    @Test
    public void customerIdCannotBeEmpty() {
        try {
            new ShoppingCart("");
        } catch (IllegalArgumentException e) {
            assertEquals("Customer ID cannot be empty", e.getMessage());
        }
    }

    @Test
    public void customerIdMustBeValidFormat() {
        try {
            new ShoppingCart("ABC12345DE-1");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid customer ID format", e.getMessage());
        }
    }

    @Test
    public void testCustomerId_t0() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABCD12345DE-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t1() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("AB112345EF-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t2() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("AB12345EF-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t3() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABC123456EF-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t4() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABC1234AEF-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t5() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABC1234EF-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t6() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABC12345EFG-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t7() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABC12345E1-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t8() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABC12345E-A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t9() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABC12345EF_A"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void testCustomerId_t10() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new ShoppingCart("ABC12345EF-X"));
        assertEquals("Invalid customer ID format", exception.getMessage());
    }

    @Test
    public void addItemAddsItem() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        cart.addItem("apple", 5);
        assertEquals(5, cart.items().get("apple"));
    }

    @Test
    public void removeItemRemovesItem() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        cart.addItem("apple", 5);
        cart.removeItem("apple", 3);
        assertEquals(2, cart.items().get("apple"));
    }

    @Test
    public void itemNameNoSymbols() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("$$$", 99);
        } catch (IllegalArgumentException e) {
            assertEquals("Item names must only contain letters, numbers, and spaces.", e.getMessage());
        }
    }

    @Test
    public void itemNameOtherLanguages() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("Hello 你好 привет مرحبًا", 99);
        } catch (IllegalArgumentException e) {
            assertEquals("Item names must only contain letters, numbers, and spaces.", e.getMessage());
        }
    }

    @Test
    public void itemNameTooLong() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("0123456789 0123456789 0123456789 0123456789 0123456789", 99);
            System.err.println("\n\n\n\n\n\n\ngottem");
        } catch (IllegalArgumentException e) {
            assertEquals("Item name is too long", e.getMessage());
        }
    }

    @Test
    public void itemNameTooShort() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("", 99);
            System.err.println("\n\n\n\n\n\n\ngottem");
        } catch (IllegalArgumentException e) {
            assertEquals("Item name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void itemMustBeInCatalog() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("skibidi toilet", 69);
        } catch (IllegalArgumentException e) {
            assertEquals("Item is not in the catalog", e.getMessage());
        }
    }

    @Test
    public void cannotAddZeroItems() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void cannotAddNegativeItems() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void cannotAddMoreThan100Items() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 101);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void cannotRemoveZeroItems() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.removeItem("apple", 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be greater than 0", e.getMessage());
        }
    }

    @Test
    public void cannotRemoveNegativeItems() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.removeItem("apple", -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be greater than 0", e.getMessage());
        }
    }

    @Test
    public void cannotIncrementOver100() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 100);
            cart.addItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void cannotDecrementBelow1() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 1);
            cart.removeItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void itemNotInCartCannotBeRemoved() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.removeItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Item does not exist in cart", e.getMessage());
        }
    }

    @Test
    public void cannotRemoveMoreItemsThanInCart() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 1);
            cart.removeItem("apple", 2);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity exceeds the quantity of the item in the cart. You have 1.", e.getMessage());
        }
    }
}