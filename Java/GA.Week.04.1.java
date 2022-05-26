import java.util.*;

abstract class StringOperations {
    public abstract String reverse(String s);

    public abstract int vowelCount(String s);
}

abstract class StringReverse extends StringOperations {

    public String reverse(String s) {
        String resStr = "";

        for (int i = s.length() - 1; i >= 0; i--) {
            resStr += s.charAt(i);
        }

        return resStr;
    }
}

class UpdatedStrings extends StringReverse {

    public int vowelCount(String s) {

        String str = s.toLowerCase();
        int vCount = 0;

        for (int i = 0; i <= str.length() - 1; i++) {

            char chr = str.charAt(i);
            if (chr == 'a' || chr == 'e' || chr == 'i' || chr == 'o' || chr == 'u') {
                vCount += 1;
            }
        }
        return vCount;
    }

}

class Example {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        UpdatedStrings str = new UpdatedStrings();
        System.out.println("Reverse of " + s + " is " + str.reverse(s));
        System.out.println("Vowel count of " + s + " is " + str.vowelCount(s));
    }
}