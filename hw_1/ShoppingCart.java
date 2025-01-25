package hw_1;
import java.util.*;
import java.util.regex.Pattern;

public final class ShoppingCart {
    private final UUID CART_ID;
    private final UUID CUSTOMER_ID;
    private final Map<String, Integer> items;
    private static final int MAX_QUANTITY = 100;
    private static final int MAX_ITEM_NAME_LENGTH = 50;
    private static final Pattern ITEM_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]{1,50}$");
    private static final Set<String> CATALOG = Set.of("apple", "banana", "orange", "laptop", "book");


    public ShoppingCart(UUID customerID){
        this.CART_ID = UUID.randomUUID();
        this.CUSTOMER_ID = customerID;
        this.items = new HashMap<String, Integer>();
    }

    public UUID getCartId() {
        return this.CART_ID;
    }

    public String getCustomerId() {
        return this.CUSTOMER_ID.toString();
    }

    public Map<String, Integer> getItems() {
        return Collections.unmodifiableMap(new HashMap<>(items));
    }

    public void addItem(String itemName, int quantity) {
        itemName = itemName.toLowerCase();
        validateItem(itemName, quantity);
        items.put(itemName, items.getOrDefault(itemName, 0) + quantity);
    }

    public void removeItem(String itemName, int quantity) {
        if (!items.containsKey(itemName)) {
            throw new IllegalArgumentException("Item does not exist in cart");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        } 
        if (quantity > getItemQuantity(itemName)) {
            throw new IllegalArgumentException("Quantity exceeds the quantity of the item in the cart");
        }
        items.put(itemName, items.get(itemName) - quantity);
    }

    private int getItemQuantity(String itemName) {
        if (!items.containsKey(itemName)) {
            throw new IllegalArgumentException("Item does not exist in cart");
        }
        return items.get(itemName);
    }

    private void validateItem(String itemName, int quantity) {
        if (!CATALOG.contains(itemName.toLowerCase())) {
            throw new IllegalArgumentException("Invalid item. Not in catalog.");
        }
        if (!ITEM_NAME_PATTERN.matcher(itemName).matches()) {
            throw new IllegalArgumentException("Invalid item name format");
        }
        if (quantity <= 0 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Quantity must be between 1 and " + MAX_QUANTITY);
        }
        if (items.containsKey(itemName)) {
            if (quantity + getItemQuantity(itemName) > MAX_QUANTITY || quantity + getItemQuantity(itemName) < 0) {
                throw new IllegalArgumentException("Quantity exceeds maximum allowed quantity");
            }
        }
    }

}