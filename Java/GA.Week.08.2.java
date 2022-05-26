import java.util.*;

class Items implements Cloneable {
    public String[] item;

    public Items(String[] itemName) {
        this.item = itemName;
    }

    @Override
    public String toString() {
        String output = "";
        for (String it : item) {
            output += it + " ";
        }

        return output;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        String[] cloneItemsList = Arrays.copyOf(item, item.length);
        Items cloneItems = new Items(cloneItemsList);
        return cloneItems;
    }
}

class Customer implements Cloneable {

    String name;
    Items items;

    Customer(String name, Items items) {
        this.name = name;
        this.items = items;
    }

    public void setName(String s) {
        this.name = s;
    }

    @Override
    public String toString() {
        return name + " " + items;
    }

    public Items getItems() {
        return items;
    }

    @Override
    protected Customer clone() throws CloneNotSupportedException {
        Customer cs = (Customer) super.clone();
        cs.items = (Items) items.clone();
        return cs;
    }
}

public class Order {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // number of items
        String[] itm = new String[n];
        for (int i = 0; i < n; i++) {
            itm[i] = sc.next(); // list of items
        }
        var c1 = new Customer("naresh", new Items(itm));
        Customer c2 = c1.clone();
        c2.getItems().item[0] = sc.next(); // Update first item of c2
        c2.setName("suresh"); // Update name of c2
        System.out.println(c1);
        System.out.println(c2);
    }
}