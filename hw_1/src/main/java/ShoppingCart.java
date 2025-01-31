package hw_1;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class ShoppingCart {
    private final UUID CART_ID;
    private final String CUSTOMER_ID;
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("^\\p{L}{3}[\\d]{5}[a-zA-Z]{2}[-][AQ]$");
    private final Map<String, Integer> items;
    private static final int MAX_QUANTITY = 100;
    private static final int MAX_ITEM_NAME_LENGTH = 50;
    private static final int MAX_CART_SIZE = 200;
    private static final double MAX_CART_TOTAL = 2500.0;
    private static final Pattern ITEM_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]{1,50}$");
    private static final Set<String> CATALOG = Set.of("apple", "banana", "orange", "laptop", "book");
    private static final Map<String, Double> PRICES = Map.of("apple", 0.5, "banana", 0.3, "orange", 0.4, "laptop",
            1000.0, "book", 20.0);

    public ShoppingCart(String customerID) {
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

        int totalItemQuantity = items.getOrDefault(itemName, 0) + quantity;
        validateQuantity(totalItemQuantity);
        validateTotalCost(itemName, quantity);

        items.put(itemName, totalItemQuantity);
    }

    public void removeItem(String itemName, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (!items.containsKey(itemName)) {
            throw new IllegalArgumentException("Item does not exist in cart");
        }

        int itemQuantity = getItemQuantity(itemName);

        if (quantity > itemQuantity) {
            throw new IllegalArgumentException(String.format(
                    "Quantity exceeds the quantity of the item in the cart. You have %d.", itemQuantity));
        }

        int newQuantity = itemQuantity - quantity;
        if (newQuantity == 0) {
            items.remove(itemName);
        } else {
            items.put(itemName, newQuantity);
        }
    }

    public double totalCost() {
        double total = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            total += PRICES.get(entry.getKey()) * entry.getValue();
        }
        return total;
    }

    public int size() {
        int size = 0;
        for (int itemQuantity : items.values()) {
            size += itemQuantity;
        }
        return size;
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
        if (itemName.length() > MAX_ITEM_NAME_LENGTH) {
            throw new IllegalArgumentException("Item name is too long");
        }
        if (itemName.isBlank()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        if (!ITEM_NAME_PATTERN.matcher(itemName).matches()) {
            throw new IllegalArgumentException("Item names must only contain letters, numbers, and spaces.");
        }
        if (!CATALOG.contains(itemName.toLowerCase())) {
            throw new IllegalArgumentException("Item is not in the catalog");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity <= 0 || quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(String.format(
                    "Quantity must be between 1 and %d", MAX_QUANTITY));
        }

        if (size() + quantity > MAX_CART_SIZE) {
            throw new IllegalArgumentException(String.format(
                    "Total number of items cannot exceed %d", MAX_CART_SIZE));
        }
    }

    private void validateTotalCost(String itemName, int quantity) {
        if (totalCost() + PRICES.get(itemName) * quantity > MAX_CART_TOTAL) {
            throw new IllegalArgumentException(String.format(
                    "Total cost of cart cannot exceed %.2f", MAX_CART_TOTAL));
        }
    }

}