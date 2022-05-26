import java.util.*;
interface Searchable{
    public int search(int start_index, Object key);
}
class Char{
    private char c;
    public Char(char c_) {
        c = c_;
    }
    public boolean equals(Object d) {
                if (d instanceof Char) {
            Char chr = (Char) d;
            return (c == chr.c);
        }
        return false;
    }
}

class CharArray implements Searchable {
    private Char[] carr;

    public CharArray(Char[] carr_) {
        carr = carr_;
    }

    public int search(int start_index, Object key) {
        for (int i = start_index; i < carr.length; i++) {
            if (carr[i].equals(key)) {
                return i;
            }
        }
        return -1;
    }
}

class FrequencyCounter {
    public static int getFrequency(Searchable ob, Object key) {
        if (ob instanceof CharArray) {
            int num = 0, i = 0;
            i = ob.search(i, key);
            while (i > -1) {
                if (i != -1) {
                    num += 1;
                }
                i = ob.search(i + 1, key);
            }
            return num;
        } else
            return 0;
    }
}
class FClass{
    public static void main(String[] args) {
        String str;
        char c;
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
        c = sc.next().charAt(0);
        Char key = new Char(c);
        Char[] cA = new Char[str.length()]; 
        for(int i = 0; i < str.length(); i++) {
            cA[i] = new Char(str.charAt(i));
        }
        CharArray caObj = new CharArray(cA);
        System.out.println(FrequencyCounter.getFrequency(caObj, key));
    }
}