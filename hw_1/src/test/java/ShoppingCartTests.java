package hw_1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

public class ShoppingCartTests {
    private static final String CUSTOMER_ID = "ABC12345DE-A";

    @BeforeAll
    public static void initializeStore() {
        Store.createCart(CUSTOMER_ID);
    }

    @BeforeEach
    public void clearCart() {
        Store.clearCart(CUSTOMER_ID);
        assertEquals(0, Store.cartSize(CUSTOMER_ID));
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

    @Test
    public void createCartEmptyCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart(""));
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }

    @Test
    public void itemsEmptyCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items(""));
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }

    @Test
    public void addItemEmptyCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("", "apple", 1));
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }
    
    @Test
    public void removeItemEmptyCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("", "apple", 1));
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }

    @Test
    public void totalCostEmptyCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost(""));
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }

    @Test
    public void cartSizeEmptyCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize(""));
        assertEquals("Customer ID cannot be empty", exception.getMessage());
    }

    @Test
    public void createCartInvalidEndCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC12345DE-1"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsInvalidEndCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC12345DE-1"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemInvalidEndCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC12345DE-1", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemInvalidEndCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC12345DE-1", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostInvalidEndCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC12345DE-1"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeInvalidEndCustomerID() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC12345DE-1"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void createCartCustomerID_part1TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABCD12345DE-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part1TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABCD12345DE-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part1TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABCD12345DE-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part1TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABCD12345DE-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part1TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABCD12345DE-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part1TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABCD12345DE-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_part1InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("AB112345EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part1InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("AB112345EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part1InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("AB112345EF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part1InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("AB112345EF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part1InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("AB112345EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part1InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("AB112345EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_part1TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("AB12345EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part1TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("AB12345EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part1TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("AB12345EF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part1TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("AB12345EF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part1TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("AB12345EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part1TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("AB12345EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void createCartCustomerID_part2TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC123456EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part2TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC123456EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part2TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC123456EF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part2TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC123456EF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part2TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC123456EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part2TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC123456EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_part2InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC1234AEF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part2InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC1234AEF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part2InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC1234AEF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part2InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC1234AEF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part2InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC1234AEF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part2InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC1234AEF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_part2TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC1234EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part2TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC1234EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part2TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC1234EF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part2TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC1234EF-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part2TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC1234EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part2TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC1234EF-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void createCartCustomerID_part3TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC12345EFG-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part3TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC12345EFG-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part3TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC12345EFG-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part3TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC12345EFG-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part3TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC12345EFG-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part3TooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC12345EFG-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_part3InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC12345E1-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part3InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC12345E1-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part3InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC12345E1-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part3InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC12345E1-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part3InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC12345E1-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part3InvalidCharacter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC12345E1-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_part3TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC12345E-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_part3TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC12345E-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_part3TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC12345E-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_part3TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC12345E-A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_part3TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC12345E-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_part3TooShort() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC12345E-A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_underscore() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC12345EF_A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_underscore() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC12345EF_A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_underscore() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC12345EF_A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_underscore() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC12345EF_A", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_underscore() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC12345EF_A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_underscore() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC12345EF_A"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_missingDash() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC12345EFA"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_missingDash() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC12345EFA"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_missingDash() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC12345EFA", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_missingDash() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC12345EFA", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_missingDash() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC12345EFA"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_missingDash() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC12345EFA"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_invalidEndLetter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.createCart("ABC12345EF-X"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void itemsCustomerID_invalidEndLetter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.items("ABC12345EF-X"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void addItemCustomerID_invalidEndLetter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem("ABC12345EF-X", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void removeItemCustomerID_invalidEndLetter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem("ABC12345EF-X", "apple", 1));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void totalCostCustomerID_invalidEndLetter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.totalCost("ABC12345EF-X"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }

    @Test
    public void cartSizeCustomerID_invalidEndLetter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.cartSize("ABC12345EF-X"));
        assertEquals("Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>",
                exception.getMessage());
    }
    
    @Test
    public void createCartCustomerID_allowsQ() {
        Store.createCart("ABC12345EF-Q");
        assertEquals(0, Store.cartSize("ABC12345EF-Q"));
    }

    @Test
    public void itemsCustomerID_allowsQ() {
        assertNotNull(Store.items("ABC12345EF-Q"));
    }

    @Test
    public void addRemoveItemCustomerID_allowsQ() {
        Store.addItem("ABC12345EF-Q", "apple", 1);
        assertEquals(1, Store.cartSize("ABC12345EF-Q"));
        assertEquals(0.5, Store.totalCost("ABC12345EF-Q"));

        Store.removeItem("ABC12345EF-Q", "apple", 1);
        assertEquals(0, Store.cartSize("ABC12345EF-Q"));
        assertEquals(0, Store.totalCost("ABC12345EF-Q"));
    }

    @Test
    public void addItemDuplicateItem() {
        Store.addItem(CUSTOMER_ID, "apple", 5);
        assertEquals(5, Store.cartSize(CUSTOMER_ID));
        Map<String, Integer> expectedItems = Map.of("apple", 5);
        assertTrue(expectedItems.equals(Store.items(CUSTOMER_ID)));
    }

    @Test
    public void addItemMultipleItems() {
        Store.addItem(CUSTOMER_ID, "apple", 5);
        Store.addItem(CUSTOMER_ID, "book", 2);
        assertEquals(7, Store.cartSize(CUSTOMER_ID));
        Map<String, Integer> expectedItems = Map.of("apple", 5, "book", 2);
        assertTrue(expectedItems.equals(Store.items(CUSTOMER_ID)));
    }

    @Test
    public void testRemoveSomeOfItem() {
        Store.addItem(CUSTOMER_ID, "apple", 5);
        Store.removeItem(CUSTOMER_ID, "apple", 3);
        assertEquals(2, Store.cartSize(CUSTOMER_ID));
        Map<String, Integer> expectedItems = Map.of("apple", 2);
        assertTrue(expectedItems.equals(Store.items(CUSTOMER_ID)));
    }

    @Test
    public void testRemoveItem() {
        Store.addItem(CUSTOMER_ID, "apple", 5);
        Store.removeItem(CUSTOMER_ID, "apple", 5);
        assertEquals(0, Store.cartSize(CUSTOMER_ID));
        Map<String, Integer> expectedItems = new HashMap<>();
        assertTrue(expectedItems.equals(Store.items(CUSTOMER_ID)));
    }

    @Test
    public void itemsAreImmutable() {
        Store.addItem(CUSTOMER_ID, "apple", 5);
        Map<String, Integer> items = Store.items(CUSTOMER_ID);
        assertThrows(UnsupportedOperationException.class,
                () -> items.put("book", 2));
    }

    @Test
    public void itemNameNoSymbols() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "$$$", 99));
        assertEquals("Item names must only contain letters, numbers, and spaces", exception.getMessage());
    }

    @Test
    public void itemNameOtherLanguages() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "Hello 你好 привет مرحبًا", 99));
        assertEquals("Item names must only contain letters, numbers, and spaces", exception.getMessage());
    }

    @Test
    public void itemNameTooLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "0123456789 0123456789 0123456789 0123456789 0123456789", 99));
        assertEquals("Item name cannot be more than 50 characters", exception.getMessage());
    }

    @Test
    public void itemNameEmpty() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "", 99));
        assertEquals("Item name cannot be empty", exception.getMessage());
    }

    @Test
    public void itemMustBeInCatalog() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "skibidi toilet", 69));
        assertEquals("Item is not in the catalog", exception.getMessage());
    }

    @Test
    public void catalogIsImmutable() {
        Map<String, Double> catalog = Store.catalog();
        assertThrows(UnsupportedOperationException.class,
                () -> catalog.put("hat", 10.0));
    }

    @Test
    public void cannotAddZeroItems() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "apple", 0));
        assertEquals("Quantity must be between 1 and 100", exception.getMessage());
    }

    @Test
    public void cannotAddNegativeItems() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "apple", -1));
        assertEquals("Quantity must be between 1 and 100", exception.getMessage());
    }

    @Test
    public void cannotAddMoreThan100Items() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "apple", 101));
        assertEquals("Quantity must be between 1 and 100", exception.getMessage());
    }

    @Test
    public void cannotRemoveZeroItems() {
        Store.addItem(CUSTOMER_ID, "apple", 1);
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem(CUSTOMER_ID, "apple", 0));
        assertEquals("Quantity must be greater than 0", exception.getMessage());
    }

    @Test
    public void cannotRemoveNegativeItems() {
        Store.addItem(CUSTOMER_ID, "apple", 1);
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem(CUSTOMER_ID, "apple", -1));
        assertEquals("Quantity must be greater than 0", exception.getMessage());
    }

    @Test
    public void cannotIncrementOver100() {
        Store.addItem(CUSTOMER_ID, "apple", 100);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Store.addItem(CUSTOMER_ID, "apple", 1);
        });
        assertEquals("Quantity must be between 1 and 100", exception.getMessage());
    }

    @Test
    public void itemNotInCartCannotBeRemoved() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.removeItem(CUSTOMER_ID, "apple", 1));
        assertEquals("Item does not exist in cart", exception.getMessage());
    }

    @Test
    public void cannotRemoveMoreItemsThanInCart() {
        Store.addItem(CUSTOMER_ID, "apple", 1);
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Store.removeItem(CUSTOMER_ID, "apple", 2);
        });
        assertEquals("Quantity exceeds the quantity of the item in the cart. You have 1.", exception.getMessage());
    }

    @Test
    public void totalCostEmptyCart() {
        assertEquals(0, Store.totalCost(CUSTOMER_ID));
    }

    @Test
    public void totalCostSingleItem() {
        Store.addItem(CUSTOMER_ID, "apple", 1);
        assertEquals(0.5, Store.totalCost(CUSTOMER_ID));
    }

    @Test
    public void totalCostDuplicateItem() {
        Store.addItem(CUSTOMER_ID, "apple", 5);
        assertEquals(2.5, Store.totalCost(CUSTOMER_ID));
    }

    @Test
    public void totalCostMultipleItems() {
        Store.addItem(CUSTOMER_ID, "laptop", 1);
        Store.addItem(CUSTOMER_ID, "book", 1);
        Store.addItem(CUSTOMER_ID, "banana", 1);
        Store.addItem(CUSTOMER_ID, "orange", 1);
        assertEquals(1020.7, Store.totalCost(CUSTOMER_ID));
    }

    @Test
    public void totalCostMultipleDuplicateItems() {
        Store.addItem(CUSTOMER_ID, "laptop", 1);
        Store.addItem(CUSTOMER_ID, "book", 3);
        Store.addItem(CUSTOMER_ID, "banana", 1);
        Store.addItem(CUSTOMER_ID, "banana", 1);
        Store.addItem(CUSTOMER_ID, "orange", 2);
        assertEquals(1061.4, Store.totalCost(CUSTOMER_ID));
    }

    @Test
    public void totalCostUpperBound() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "laptop", 3));
        assertEquals("Total cost of cart cannot exceed 2500.00", exception.getMessage());
        assertEquals(0, Store.cartSize(CUSTOMER_ID));
    }

    @Test
    public void totalCostUpperBoundIncremental() {
        Store.addItem(CUSTOMER_ID, "laptop", 2);

        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "laptop", 1));
        assertEquals("Total cost of cart cannot exceed 2500.00", exception.getMessage());
        assertEquals(2, Store.cartSize(CUSTOMER_ID));
    }

    @Test
    public void cartSizeUpperBound() {
        Store.addItem(CUSTOMER_ID, "apple", 100);
        Store.addItem(CUSTOMER_ID, "orange", 100);
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> Store.addItem(CUSTOMER_ID, "banana", 1));
        assertEquals("Total number of items cannot exceed 200", exception.getMessage());
    }
}