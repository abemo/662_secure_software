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
    
}
