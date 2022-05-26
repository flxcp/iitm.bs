import java.util.*;

//define user defined exception InvalidInputEx
class InvalidInputEx extends Exception {
    public InvalidInputEx(String er) {
        super(er);
    }
}

// define the class IntList with
class IntList {
    // instance variable of int[]
    private int[] arr = new int[5];

    // and methods set_value, getArray()
    public void set_value(int idx, int val) throws InvalidInputEx {
        try {
            arr[idx] = val;
        } catch (ArrayIndexOutOfBoundsException e) {
            InvalidInputEx ex = new InvalidInputEx("invalid index input");
            ex.initCause(e);
            throw ex;
        }
    }

    public int[] getArray() {
        return arr;
    }
}

class FClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IntList ilist = new IntList();
        try {
            for (int i = 0; i < 5; i++) {
                int n = sc.nextInt();
                int m = sc.nextInt();
                ilist.set_value(n, m);
            }
        } catch (InvalidInputEx e) {
            System.out.println(e.getMessage());
            Throwable ori = e.getCause();
            System.out.println(ori.getMessage());
        }
        int[] i_arr = ilist.getArray();
        for (int i = 0; i < i_arr.length; i++)
            System.out.print(i_arr[i] + " ");
    }
}