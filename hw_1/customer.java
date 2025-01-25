package hw_1;
import java.util.*;
import java.util.regex.Pattern;

public class Customer {
    private final UUID CUSTOMER_ID;
    private final ArrayList<ShoppingCart> shoppingCarts;

    public Customer(){
        this.CUSTOMER_ID = UUID.randomUUID();
        this.shoppingCarts = new ArrayList<ShoppingCart>();
    }

    public void createNewShoppingCart(){
        ShoppingCart cart = new ShoppingCart(this.CUSTOMER_ID);
        shoppingCarts.add(cart);
    }

    public ArrayList<ShoppingCart> getShoppingCarts(){
        return new ArrayList<>(shoppingCarts);
    }

    public UUID getCustomerId() {
        return this.CUSTOMER_ID;
    }
}
