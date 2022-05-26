import java.util.*;

class RemoveStudent {
    public boolean property(Double value) {
        if (value < 65)
            return true;
        return false;
    }

    public void detained(Map<String, Double> obj) {
        Iterator<Map.Entry<String, Double>> iterator = obj.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Double> student = iterator.next();
            if (property(student.getValue())) {
                iterator.remove();
            }
        }
        display(obj);
    }

    public void display(Map<String, Double> obj) {
        System.out.println(obj);
    }
}

public class Test {
    public static void main(String[] args) {
        Map<String, Double> map = new TreeMap<String, Double>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 6; i++) {
            map.put(scanner.next(), scanner.nextDouble());
        }
        RemoveStudent obj = new RemoveStudent();
        obj.detained(map);
    }
}