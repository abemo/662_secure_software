/*
 * NUM00-J. Detect or prevent integer overflow
 * https://wiki.sei.cmu.edu/confluence/display/java/NUM00-J.+Detect+or+prevent+integer+overflow 
 * 
 * Do not allow mathematical operations (addition, multiplication, exponentiation, etc) to overflow the integer 
 * range of their primitive integer types.
 * 
 * You must still be careful even with unary operations, as they can still overflow for signed types due to the
 * magnitude of the negative end of the range being one larger than the positive end of the range (eg -128 to 127 for byte).
 */

package HW2;

public class intoverflow {
  // an example of an unsafe method which returns the next fibonacci number
  public static int nextFibonacci(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }
    return nextFibonacci(n - 1) + nextFibonacci(n - 2);
  }

  // a compliant version of the above method
  public static int nextFibonacciSafe(int n) {
    if (n == 0) {
      return 0;
    } else if (n == 1) {
      return 1;
    }
    return Math.addExact(nextFibonacciSafe(n - 1), nextFibonacciSafe(n - 2));
  }

  public static void main(String[] args) {
    System.out.println(nextFibonacci(47)); 
    System.out.println(nextFibonacciSafe(47)); 
  }

}
