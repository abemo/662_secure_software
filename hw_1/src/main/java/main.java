package hw_1;

import java.util.*;

import hw_1.ShoppingCart;

public class main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart("ABC12345DE-A");
        cart.addItem("APPLE", 5);
        cart.addItem("apple", 5);
        cart.removeItem("apple", -1);
        cart.addItem("banana", 100);
        cart.addItem("banana", 0);
        System.out.println(cart.items());
        System.out.println("------------------------------------");
        cart.removeItem("apple", 3);
        System.out.println(cart.items());
        System.out.println("------------------------------------");
        // cart.removeItem("apple", 100);
        // System.out.println(cart.items());
        // System.out.println("------------------------------------");
        UUID cartId = cart.cartId();
        System.out.println("cartId: " + cartId);
        String customerId = cart.customerId();
        System.out.println("customerId: " + customerId);
        System.out.println("customerId: " + cart.customerId());
        System.out.println("------------------------------------");

    }
}