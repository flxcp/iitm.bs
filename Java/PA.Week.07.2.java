import java.util.*;

class FClass {

    public static char[] replace(char[] arr, int i, char ch) {
        try {
            arr[i] = ch;
            return arr;
        } catch (ArrayIndexOutOfBoundsException e) {
            if (i >= arr.length) {
                arr[arr.length - 1] = ch;
                return arr;
            } else {
                throw e;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        int i = sc.nextInt();
        char c = sc.next().charAt(0);
        try {
            String s2 = new String(replace(s1.toCharArray(), i, c));
            System.out.println(s2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}