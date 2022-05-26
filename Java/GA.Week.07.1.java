import java.util.*;

class DivisionException extends Exception {
    public String toString() {
        return "Division with 3 is not allowed";
    }
}

public class Test {

    // Define divide(int a, int b) here
    public static int divide(int a, int b) throws DivisionException {
        if (b == 3) {
            throw new DivisionException();
        } else {
            return a / b;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();

        try {
            int c = divide(x, y);
            System.out.println(c);
        } catch (DivisionException e) {
            System.out.println(e);
        }

    }
}