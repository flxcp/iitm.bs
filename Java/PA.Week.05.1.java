import java.lang.reflect.*;
import java.util.*;

class ClassStats {
    public static int getPubMethodCount(String cname) {
        try {
            Class c = Class.forName(cname);
            Method[] mA = c.getMethods();
            return mA.length;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getAllMethodCount(String cname) {
        try {
            Class c = Class.forName(cname);
            Method[] mA = c.getDeclaredMethods();
            return mA.length;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getPubFieldCount(String cname) {
        try {
            Class c = Class.forName(cname);
            Field[] mF = c.getFields();
            return mF.length;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getAllFieldCount(String cname) {
        try {
            Class c = Class.forName(cname);
            Field[] mF = c.getDeclaredFields();
            return mF.length;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getPubContCount(String cname) {
        try {
            Class c = Class.forName(cname);
            Constructor[] mC = c.getConstructors();
            return mC.length;
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getAllContCount(String cname) {
        try {
            Class c = Class.forName(cname);
            Constructor[] mC = c.getDeclaredConstructors();
            return mC.length;
        } catch (Exception e) {
            return -1;
        }
    }
}

class FClass {
    public static void main(String[] args) {
        String cname;
        Scanner sc = new Scanner(System.in);
        cname = sc.nextLine();
        System.out.println("Constructor: " +
                ClassStats.getPubContCount(cname) + ", " +
                ClassStats.getAllContCount(cname));
        System.out.println("Fields: " +
                ClassStats.getPubFieldCount(cname) + ", " +
                ClassStats.getAllFieldCount(cname));
        System.out.println("Methods: " +
                ClassStats.getPubMethodCount(cname) + ", " +
                ClassStats.getAllMethodCount(cname));
    }
}