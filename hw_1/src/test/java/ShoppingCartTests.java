package hw_1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ShoppingCartTests {
    private static final String CUSTOMER_ID = "ABC12345DE-A";

    @BeforeAll
    public static void initializeStore() {
        Store.createCart(CUSTOMER_ID);
    }

    @Test
    public void newCartIsEmpty() {
        assertEquals(0, Store.cartSize(CUSTOMER_ID));
    }

    @Test
    public void oneCartPerCustomer() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart(CUSTOMER_ID));
        assertEquals("Only 1 cart per customer allowed", exception.getMessage());
    }

    @Test
    public void validCustomerId() {
        Store.createCart("XHQ81739JK-Q");
        assertEquals(0, Store.cartSize("XHQ81739JK-Q"));
    }

    @Test
    public void createCartNullCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart(null));
        assertEquals("Customer ID cannot be null", exception.getMessage());
    }

    @Test
    public void itemsNullCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items(null));
        assertEquals("Customer ID cannot be null", exception.getMessage());
    }

    @Test
    public void addItemNullCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(null, "apple", 1));
        assertEquals("Customer ID cannot be null", exception.getMessage());
    }
    
    @Test
    public void removeItemNullCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem(null, "apple", 1));
        assertEquals("Customer ID cannot be null", exception.getMessage());
    }

    @Test
    public void totalCostNullCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost(null));
        assertEquals("Customer ID cannot be null", exception.getMessage());
    }

    @Test
    public void cartSizeNullCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize(null));
        assertEquals("Customer ID cannot be null", exception.getMessage());
    }

    // @Test
    // public void customerIdCannotBeEmpty() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart(""));
    //     assertEquals("Customer ID cannot be empty", exception.getMessage());
    // }

    // @Test
    // public void customerIdMustBeValidFormat() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC12345DE-1"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t0() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABCD12345DE-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t1() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("AB112345EF-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t2() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("AB12345EF-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t3() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC123456EF-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t4() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC1234AEF-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t5() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC1234EF-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t6() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC12345EFG-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t7() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC12345E1-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t8() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC12345E-A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t9() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC12345EF_A"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void testCustomerId_t10() {
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> new ShoppingCart("ABC12345EF-X"));
    //     assertEquals("Invalid customer ID format", exception.getMessage());
    // }

    // @Test
    // public void addItemAddsItem() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("apple", 5);
    //     assertEquals(5, cart.items().get("apple"));
    //     assertEquals(5, cart.size());
    // }

    // @Test
    // public void addMultipleItems() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("apple", 5);
    //     cart.addItem("book", 2);
    //     assertEquals(7, cart.size());
    // }

    // @Test
    // public void removeItemRemovesItem() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("apple", 5);
    //     cart.removeItem("apple", 3);
    //     assertEquals(2, cart.items().get("apple"));
    //     assertEquals(2, cart.size());
    // }

    // @Test
    // public void itemNameNoSymbols() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("$$$", 99));
    //     assertEquals("Item names must only contain letters, numbers, and spaces.", exception.getMessage());
    // }

    // @Test
    // public void itemNameOtherLanguages() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("Hello 你好 привет مرحبًا", 99));
    //     assertEquals("Item names must only contain letters, numbers, and spaces.", exception.getMessage());
    // }

    // @Test
    // public void itemNameTooLong() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("0123456789 0123456789 0123456789 0123456789 0123456789", 99));
    //     assertEquals("Item name is too long", exception.getMessage());
    // }

    // @Test
    // public void itemNameTooShort() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("", 99));
    //     assertEquals("Item name cannot be empty", exception.getMessage());
    // }

    // @Test
    // public void itemMustBeInCatalog() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("skibidi toilet", 69));
    //     assertEquals("Item is not in the catalog", exception.getMessage());
    // }

    // @Test
    // public void cannotAddZeroItems() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("apple", 0));
    //     assertEquals("Quantity must be between 1 and 100", exception.getMessage());
    // }

    // @Test
    // public void cannotAddNegativeItems() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("apple", -1));
    //     assertEquals("Quantity must be between 1 and 100", exception.getMessage());
    // }

    // @Test
    // public void cannotAddMoreThan100Items() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("apple", 101));
    //     assertEquals("Quantity must be between 1 and 100", exception.getMessage());
    // }

    // @Test
    // public void cannotRemoveZeroItems() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.removeItem("apple", 0));
    //     assertEquals("Quantity must be greater than 0", exception.getMessage());
    // }

    // @Test
    // public void cannotRemoveNegativeItems() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.removeItem("apple", -1));
    //     assertEquals("Quantity must be greater than 0", exception.getMessage());
    // }

    // @Test
    // public void cannotIncrementOver100() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
    //         cart.addItem("apple", 100);
    //         cart.addItem("apple", 1);
    //     });
    //     assertEquals("Quantity must be between 1 and 100", exception.getMessage());
    // }

    // @Test
    // public void testRemoveSingleItem() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("apple", 1);
    //     cart.removeItem("apple", 1);
    //     assertEquals(0, cart.size());
    // }

    // @Test
    // public void testRemoveMultipleItems() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("apple", 1);
    //     cart.addItem("apple", 1);
    //     cart.removeItem("apple", 2);
    //     assertEquals(0, cart.size());
    // }

    // @Test
    // public void itemNotInCartCannotBeRemoved() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.removeItem("apple", 1));
    //     assertEquals("Item does not exist in cart", exception.getMessage());
    // }

    // @Test
    // public void cannotRemoveMoreItemsThanInCart() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
    //         cart.addItem("apple", 1);
    //         cart.removeItem("apple", 2);
    //     });
    //     assertEquals("Quantity exceeds the quantity of the item in the cart. You have 1.", exception.getMessage());
    // }

    // @Test
    // public void totalCostEmptyCart() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     assertEquals(0, cart.totalCost());
    // }

    // @Test
    // public void totalCostSingleItem() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("apple", 1);
    //     assertEquals(0.5, cart.totalCost());
    // }

    // @Test
    // public void totalCostDuplicateItem() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("apple", 5);
    //     assertEquals(2.5, cart.totalCost());
    // }

    // @Test
    // public void totalCostMultipleItems() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("laptop", 1);
    //     cart.addItem("book", 1);
    //     cart.addItem("banana", 1);
    //     cart.addItem("orange", 1);
    //     assertEquals(1020.7, cart.totalCost());
    // }

    // @Test
    // public void totalCostMultipleDuplicateItems() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("laptop", 1);
    //     cart.addItem("book", 3);
    //     cart.addItem("banana", 1);
    //     cart.addItem("banana", 1);
    //     cart.addItem("orange", 2);
    //     assertEquals(1061.4, cart.totalCost());
    // }

    // @Test
    // public void totalCostUpperBound() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("laptop", 3));
    //     assertEquals("Total cost of cart cannot exceed 2500.00", exception.getMessage());
    //     assertEquals(0, cart.size());
    // }

    // @Test
    // public void totalCostUpperBoundIncremental() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("laptop", 2);

    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("laptop", 1));
    //     assertEquals("Total cost of cart cannot exceed 2500.00", exception.getMessage());
    //     assertEquals(2, cart.size());
    // }

    // @Test
    // public void cartSizeUpperBound() {
    //     ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
    //     cart.addItem("apple", 100);
    //     cart.addItem("orange", 100);
    //     Throwable exception = assertThrows(IllegalArgumentException.class,
    //             () -> cart.addItem("banana", 1));
    //     assertEquals("Total number of items cannot exceed 200", exception.getMessage());
    // }
}