package hw_1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShoppingCartTests {
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
    public void CustomerIdCannotBeNull() {
        try {
            new ShoppingCart(null);
        } catch (IllegalArgumentException e) {
            assertEquals("Customer ID cannot be null", e.getMessage());
        }
    }

    @Test
    public void CustomerIdCannotBeEmpty() {
        try {
            new ShoppingCart("");
        } catch (IllegalArgumentException e) {
            assertEquals("Customer ID cannot be empty", e.getMessage());
        }
    }

    @Test
    public void CustomerIdMustBeValidFormat() {
        try {
            new ShoppingCart("ABC12345DE-1");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid customer ID format", e.getMessage());
        }
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
    public void ItemNameNoSymbols() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("$$$", 99);
        } catch (IllegalArgumentException e) {
            assertEquals("Item names must only contain letters, numbers, and spaces.", e.getMessage());
        }
    }

    @Test
    public void ItemNameOtherLanguages() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("Hello 你好 привет مرحبًا", 99);
        } catch (IllegalArgumentException e) {
            assertEquals("Item names must only contain letters, numbers, and spaces.", e.getMessage());
        }
    }

    @Test
    public void ItemNameTooLong() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("0123456789 0123456789 0123456789 0123456789 0123456789", 99);
            System.err.println("\n\n\n\n\n\n\ngottem");
        } catch (IllegalArgumentException e) {
            assertEquals("Item name is too long", e.getMessage());
        }
    }

    @Test
    public void ItemNameTooShort() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("", 99);
            System.err.println("\n\n\n\n\n\n\ngottem");
        } catch (IllegalArgumentException e) {
            assertEquals("Item name cannot be empty", e.getMessage());
        }
    }

    @Test
    public void ItemMustBeInCatalog() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("skibidi toilet", 69);
        } catch (IllegalArgumentException e) {
            assertEquals("Item is not in the catalog", e.getMessage());
        }
    }

    @Test
    public void CannotAddZeroItems() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void CannotAddNegativeItems() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void CannotAddMoreThan100Items() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 101);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void CannotRemoveZeroItems() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.removeItem("apple", 0);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be greater than 0", e.getMessage());
        }
    }

    @Test
    public void CannotRemoveNegativeItems() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.removeItem("apple", -1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be greater than 0", e.getMessage());
        }
    }

    @Test
    public void CannotIncrementOver100() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 100);
            cart.addItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void CannotDecrementBelow1() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 1);
            cart.removeItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity must be between 1 and 100", e.getMessage());
        }
    }

    @Test
    public void ItemNotInCartCannotBeRemoved() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.removeItem("apple", 1);
        } catch (IllegalArgumentException e) {
            assertEquals("Item does not exist in cart", e.getMessage());
        }
    }

    @Test
    public void CannotRemoveMoreItemsThanInCart() {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        try {
            cart.addItem("apple", 1);
            cart.removeItem("apple", 2);
        } catch (IllegalArgumentException e) {
            assertEquals("Quantity exceeds the quantity of the item in the cart. You have 1.", e.getMessage());
        }
    }
}