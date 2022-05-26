import java.util.*;

class Point {
    private int x, y;

    // implement the constructor and
    // override the toString() and equals() methods
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Point p) {
        if (x == p.x && y == p.y)
            return true;
        else
            return false;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}

class FClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        Point p1 = new Point(x1, y1);
        Point p2 = new Point(x2, y2);
        if (p1.equals(p2))
            System.out.println(p1 + "==" + p2);
        else
            System.out.println(p1 + "!=" + p2);
    }
}