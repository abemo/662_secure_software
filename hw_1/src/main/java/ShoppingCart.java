package hw_1;
import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import java.util.regex.Pattern;

public final class ShoppingCart {
    private final UUID CART_ID;
    private final String CUSTOMER_ID;
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("^[a-zA-Z]{3}[\\d]{5}[a-zA-Z]{2}[-][AQ]$");
    private final Map<String, Integer> items;
    private static final int MAX_QUANTITY = 100;
    private static final int MAX_ITEM_NAME_LENGTH = 50;
    private static final double MAX_CART_TOTAL = 2500.0;
    private static final Pattern ITEM_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]{1,50}$");
    private static final Set<String> CATALOG = Set.of("apple", "banana", "orange", "laptop", "book");
    private static final Map<String, Double> PRICES = Map.of("apple", 0.5, "banana", 0.3, "orange", 0.4, "laptop", 1000.0, "book", 20.0);


    public ShoppingCart(String customerID){
        validateCustomerID(customerID);
        this.CART_ID = UUID.randomUUID();
        this.CUSTOMER_ID = customerID;
        this.items = new HashMap<>();
    }

    public UUID cartId() { 
        return this.CART_ID;
    }

    public String customerId() {
        return this.CUSTOMER_ID;
    }

    public Map<String, Integer> items() {
        return Collections.unmodifiableMap(new HashMap<>(items));
    }

    public void addItem(String itemName, int quantity) {
        itemName = itemName.toLowerCase();
        validateItemName(itemName);
        validateQuantity(quantity);
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

    public double totalCost() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            total += PRICES.get(entry.getKey()) * entry.getValue();
        }
        if (total < 0) {
            throw new IllegalStateException("Total cost cannot be negative");
        }
        if (total > MAX_CART_TOTAL) {
            throw new IllegalStateException("Total cost exceeds the maximum allowed");
        }
        return total;
    }

    private int getItemQuantity(String itemName) {
        if (!items.containsKey(itemName)) {
            throw new IllegalArgumentException("Item does not exist in cart");
        }
        return items.get(itemName);
    }

    private void validateCustomerID(String customerID) {
        if (customerID == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (customerID.isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (!CUSTOMER_ID_PATTERN.matcher(customerID).matches()) {
            throw new IllegalArgumentException("Invalid customer ID format");
        }
    }

    private void validateItemName(String itemName) {
        if (!CATALOG.contains(itemName.toLowerCase())) {
            throw new IllegalArgumentException("Invalid item. Not in catalog.");
        }
        if (!ITEM_NAME_PATTERN.matcher(itemName).matches()) {
            throw new IllegalArgumentException("Invalid item name format");
        }
        if (itemName.length() > MAX_ITEM_NAME_LENGTH) {
            throw new IllegalArgumentException("Item name is too long");
        }
        if (itemName.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException("Quantity must be between 1 and " + MAX_QUANTITY);
        }
    }

}