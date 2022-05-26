import java.util.*;

public class SeriesSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int sum = 0;
        int loopn = n;

        while (loopn > 0) {

            for (int i = 0; i <= loopn; i++) {
                sum += i * i;
            }

            loopn -= 1;
        }
        System.out.println(sum);

    }
}