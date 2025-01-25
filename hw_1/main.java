package hw_1;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer.createNewShoppingCart();

        ShoppingCart cart = customer.getShoppingCarts().get(0);
        cart.addItem("APPLE", 5);
        cart.addItem("apple", 5);
        cart.addItem("banana", 100);
        // cart.addItem("banana", 1);
        System.out.println(cart.getItems());
        System.out.println("------------------------------------");
        UUID cartId = cart.getCartId();
        System.out.println(cartId);
        cartId = UUID.randomUUID();
        System.out.println(cartId);
        System.out.println(cart.getCartId());
        System.out.println("------------------------------------");
        UUID customerId = customer.getCustomerId();
        System.out.println(customerId);
        customerId = UUID.randomUUID();
        System.out.println(customerId);
        System.out.println(customer.getCustomerId());

    }
}