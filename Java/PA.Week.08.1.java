import java.util.*;

/*
Write a program to clone an object e1 of class  Employee by implementing the interface Cloneable. After cloning,
update the department and the address of  e1. Complete the program as detailed below to achieve this functionality.   
Define classes Address and Department that implement the interface Cloneable, and have the following members: 
In both classes, add an instance variable of String type (to store the address and the department respectively) 
Implement the required constructor(s) and accessors. 
Override the method clone.
Define a class Person that implements the interface Cloneable, and has the following members:
Instance variables name of type String and addr of type Address	
Implement the required constructor(s) and accessors
Override the method clone
*/
class Address implements Cloneable {
    String ad;

    Address(String a) {
        ad = a;
    }

    public String getad() {
        return this.ad;
    }

    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }
}

// define class Address
class Department implements Cloneable {
    String dep;

    Department(String d) {
        dep = d;
    }

    public String getdep() {
        return this.dep;
    }

    public Department clone() throws CloneNotSupportedException {
        return (Department) super.clone();
    }
}

// define class Department
class Person implements Cloneable {
    String name;
    Address addr;

    Person(String n, Address a) {
        this.name = n;
        this.addr = a;
    }

    public String getname() {
        return this.name;
    }

    public Address getadd() {
        return this.addr;
    }

    public Person clone() throws CloneNotSupportedException {
        Person newp = (Person) super.clone();
        Address newa = this.addr.clone();
        newp.addr = newa;
        return newp;
    }
}

// define class Person
/*
 * Vinay Kota HR
 * Mumbai Finance
 * 
 * Vinay : Mumbai : Finance, Vinay : Kota : HR
 */
class Employee extends Person implements Cloneable {
    Department dept;

    Employee(String n, Address a, Department d) {
        super(n, a);
        this.dept = d;
    }

    public Department getdep() {
        return this.dept;
    }

    public void updateEmp(String a, String d) {
        this.addr = new Address(a);
        this.dept = new Department(d);
    }

    public String toString() {
        return name + " : " + addr.getad() + " : " + dept.getdep();
    }

    public Employee clone() throws CloneNotSupportedException {
        Employee newe = (Employee) super.clone();
        newe.dept = this.dept.clone();
        return newe;
    }
}
// define class Employee

public class FClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String n = sc.next(); // read name
        String a1 = sc.next(); // read address
        String d1 = sc.next(); // read department
        String a2 = sc.next(); // read new address
        String d2 = sc.next(); // read new department
        try {
            Employee e1 = new Employee(n, new Address(a1), new Department(d1));
            Employee e2 = e1.clone();
            e1.updateEmp(a2, d2);
            System.out.println(e1 + ", " + e2);
        } catch (CloneNotSupportedException e) {
            System.out.println("clone() not supported");
        }
    }
}