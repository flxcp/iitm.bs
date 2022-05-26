import java.util.*;

public class Test3 {
    public static boolean balanceCheck(String sequence) {
        char[] Bracket = { '{', '[', '(', ')', ']', '}' };

        StringBuilder chexpr = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            char curChar = sequence.charAt(i);

            for (char c : Bracket) {
                if (c == curChar) {
                    chexpr.append(c);
                }
            }
        }

        String expr = chexpr.toString();

        Deque<Character> stack = new ArrayDeque<Character>();

        for (int i = 0; i < expr.length(); i++) {
            char x = expr.charAt(i);

            if (x == '(' || x == '[' || x == '{') {
                stack.push(x);
                continue;
            }

            if (stack.isEmpty())
                return false;
            char check;
            switch (x) {
                case ')':
                    check = stack.pop();
                    if (check == '{' || check == '[')
                        return false;
                    break;

                case '}':
                    check = stack.pop();
                    if (check == '(' || check == '[')
                        return false;
                    break;

                case ']':
                    check = stack.pop();
                    if (check == '(' || check == '{')
                        return false;
                    break;
            }
        }

        // Check Empty Stack
        return (stack.isEmpty());
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        ArrayList<String> expr_arr = new ArrayList<String>();
        String inp = null;

        do {
            inp = s.nextLine();
            if (!inp.equalsIgnoreCase("Done"))
                expr_arr.add(inp);
        } while (!inp.equalsIgnoreCase("Done"));

        for (String expr : expr_arr) {
            if (balanceCheck(expr)) {
                System.out.println("Balanced");
            } else {
                System.out.println("Not Balanced");
            }
        }
    }
}