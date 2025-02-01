package hw_1;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class Store {
    private static Store instance = new Store();
    private static final Pattern CUSTOMER_ID_PATTERN = Pattern.compile("^\\p{L}{3}[\\d]{5}[a-zA-Z]{2}[-][AQ]$");
    private static final int MAX_QUANTITY = 100;
    private static final int MAX_ITEM_NAME_LENGTH = 50;
    private static final int MAX_CART_SIZE = 200;
    private static final double MAX_CART_TOTAL = 2500.0;
    private static final Pattern ITEM_NAME_PATTERN = Pattern.compile("^[a-zA-Z0-9 ]{1,50}$");
    private static final Set<String> CATALOG = Set.of("apple", "banana", "orange", "laptop", "book");
    private static final Map<String, Double> PRICES = Map.of("apple", 0.5, "banana", 0.3, "orange", 0.4, "laptop",
            1000.0, "book", 20.0);

    private static Map<String, ShoppingCart> carts;

    private Store() {
        carts = new HashMap<>();
    }

    public static Store Store() {
        if (instance == null) {
            instance = new Store();
        }

        return instance;
    }

    public static Map<String, Double> catalog() {
        return Collections.unmodifiableMap(new HashMap<>(PRICES));
    }

    public static void createCart(String customerID) {
        validateCustomerID(customerID);
        if (carts.containsKey(customerID)) {
            throw new IllegalArgumentException("Only 1 cart per customer allowed");
        }

        carts.put(customerID, new ShoppingCart(customerID));
    }

    public static Map<String, Integer> items(String customerID) {
        ensureCustomerExists(customerID);
        return carts.get(customerID).items();
    }

    public static void addItem(String customerID, String itemName, int quantity) {
        ensureCustomerExists(customerID);
        carts.get(customerID).addItem(itemName, quantity);
    }

    public static void removeItem(String customerID, String itemName, int quantity) {
        ensureCustomerExists(customerID);
        carts.get(customerID).removeItem(itemName, quantity);
    }

    public static double totalCost(String customerID) {
        ensureCustomerExists(customerID);
        return carts.get(customerID).totalCost();
    }

    public static int cartSize(String customerID) {
        ensureCustomerExists(customerID);
        return carts.get(customerID).size();
    }

    public static void clearCart(String customerID) {
        ensureCustomerExists(customerID);
        carts.get(customerID).clearCart();
    }

    private static void ensureCustomerExists(String customerID) {
        validateCustomerID(customerID);

        if (!carts.containsKey(customerID)) {
            throw new IllegalArgumentException(String.format("No cart exists for the customer ID %s", customerID));
        }
    }

    private static void validateCustomerID(String customerID) {
        if (customerID == null) {
            throw new IllegalArgumentException("Customer ID cannot be null");
        }
        if (customerID.isBlank()) {
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        if (!CUSTOMER_ID_PATTERN.matcher(customerID).matches()) {
            throw new IllegalArgumentException(
                    "Customer ID must have the following format: <3 letters><5 numbers><2 letters>-<A or Q>");
        }
    }

    private static final class ShoppingCart {
        private final UUID CART_ID;
        private final String CUSTOMER_ID;
        private final Map<String, Integer> items;

        private ShoppingCart(String customerID) {
            this.CART_ID = UUID.randomUUID();
            this.CUSTOMER_ID = customerID;
            this.items = new HashMap<>();
        }

        // Creates a shopping cart with a deep copy of items
        private ShoppingCart(UUID cartID, String customerID, Map<String, Integer> items) {
            this.CART_ID = cartID;
            this.CUSTOMER_ID = customerID;
            this.items = new HashMap<>(items);
        }

        private ShoppingCart deepCopy() {
            return new ShoppingCart(this.CART_ID, this.CUSTOMER_ID, this.items);
        }

        private Map<String, Integer> items() {
            return Collections.unmodifiableMap(new HashMap<>(items));
        }

        private void addItem(String itemName, int quantity) {
            itemName = itemName.toLowerCase();
            validateItemName(itemName);

            int totalItemQuantity = items.getOrDefault(itemName, 0) + quantity;
            validateQuantity(totalItemQuantity);
            validateTotalCost(itemName, quantity);

            items.put(itemName, totalItemQuantity);
        }

        private void removeItem(String itemName, int quantity) {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0");
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

        private void clearCart() {
            this.items.clear();
        }

        private double totalCost() {
            double total = 0;
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                total += PRICES.get(entry.getKey()) * entry.getValue();
            }
            return total;
        }

        private int size() {
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
}