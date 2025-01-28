package hw_1.src.main.java;

public class Item {
  private final String name;
  private final double price;

  public Item(String name, double price) {
    validateName(name);
    validatePrice(price);
    this.name = name;
    this.price = price;
  }

  private void validateName(String name) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Item name cannot be null or empty");
    }
    if (!name.matches("[a-zA-Z0-9 ]+")) {
      throw new IllegalArgumentException("Item name must be alphanumeric");
    }
    if (name.length() < 3 || name.length() > 50) {
      throw new IllegalArgumentException("Item name invalid length");
    }
  }

  private void validatePrice(double price) {
    if (price < 0) {
      throw new IllegalArgumentException("Item price cannot be negative");
    }
    if (price > 1000) {
      throw new IllegalArgumentException("Item price exceeds maximum allowed");
    }
  }
}
