import java.util.*;

class Calculator {
    // Fill the code
    public void sum(double a, double b) {
        System.out.println(a + b);
    }

    public void subtraction(double a, double b) {
        System.out.println(a - b);
    }

    public void multiply(double a, double b) {
        System.out.println(a * b);
    }

    public void division(double a, double b) {
        System.out.println(a / b);
    }
}

class UpdatedCalculator extends Calculator {
    // Fill the code
    public void remainder(double a, double b) {
        System.out.println(a % b);
    }
}

public class CalculatorCheck {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double n1 = sc.nextDouble();
        double n2 = sc.nextDouble();
        Calculator c = new Calculator();
        c.sum(n1, n2);
        c.subtraction(n1, n2);
        c.multiply(n1, n2);
        c.division(n1, n2);
        UpdatedCalculator uc = new UpdatedCalculator();
        uc.remainder(n1, n2);
    }
}