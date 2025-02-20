/*
 * NUM02-J. Ensure that division and remainder operations do not result in divide-by-zero errors
 * https://wiki.sei.cmu.edu/confluence/display/java/NUM02-J.+Ensure+that+division+and+remainder+operations+do+not+result+in+divide-by-zero+errors
 * 
 * Dividing or using modulo by zero will throw an ArithmeticException.
 * Don't do that.
 * Please.
 */

package HW2;

public class divideByZero {
    // an example of an unsafe method which returns the quotient of two numbers
    public static int divide(int a, int b) {
        return a / b;
    }

    // a compliant version of the above method
    public static int divideSafe(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("b must be non-zero");
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println(divide(10, 0));
        System.out.println(divideSafe(10, 0));
    }

}
