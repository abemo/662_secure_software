import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Test {
  private Stack stack;

  @BeforeEach
  public void setUp() {
    stack = new Stack();
  }

  @Test
  public void testStackIsEmptyOnCreation() {
    assertTrue(stack.isEmpty());
  }

  public static void main(String[] args) {
    org.junit.runner.JUnitCore.main("Test");
  }

}