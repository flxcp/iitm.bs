import java.util.*;
import java.util.stream.*;

class Employee {

    String name;
    String department;
    int salary;

    public Employee(String name, String department, int salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return name + " : " + department + " : " + salary;
    }

    public Object getDep() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

}

class FClass {
    public static Stream<Employee> query(ArrayList<Employee> emp, String department, double salary) {
        Stream<Employee> empSt = emp.stream().filter(v -> v.getDep().equals(department) && v.getSalary() >= salary);
        return empSt;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        var eList = new ArrayList<Employee>();
        eList.add(new Employee("Jack", "HR", 30000));
        eList.add(new Employee("Aria", "HR", 40000));
        eList.add(new Employee("Nora", "IT", 50000));
        eList.add(new Employee("Bella", "IT", 60000));
        eList.add(new Employee("Jacob", "IT", 70000));
        eList.add(new Employee("James", "HR", 80000));
        String d = sc.next(); // read department
        double s = sc.nextInt(); // read salary

        var st = query(eList, d, s);
        st.forEach(n -> System.out.println(n + " "));
    }
}