import java.util.*;

class Rectangle {
    int w; // width
    int h; // height
    // LINE-1: write the function setw(int) to initialize w

    public void setw(int x) {
        w = x;
    }

    // LINE-2: write the function seth(int) to initialize h
    public void seth(int y) {
        h = y;
    }

    // LINE-3: write the function area() to return area of rectangle
    public int area() {
        return w * h;
    }
}

public class FClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int w = Integer.parseInt(sc.nextLine());
        int h = Integer.parseInt(sc.nextLine());
        Rectangle r = new Rectangle();
        r.setw(w);
        r.seth(h);
        int area = r.area();
        System.out.print(area);
    }
}