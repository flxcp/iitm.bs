import java.util.*;

abstract class Account implements Comparable<Account> {
    String acc_no;
    double balance;

    public Account(String no, double bal) {
        acc_no = no;
        balance = bal;
    }

    // Override "compareTo" method here
    public int compareTo(Account obj) {
        if (obj.balance > this.balance) {
            return 1;
        } else if (obj.balance < this.balance) {
            return -1;
        } else {
            return 1;
        }
    }

    // Override "equals" method here
    public boolean equals(Object o) {
        Account obj = (Account) o;
        return this.acc_no.equals(obj.acc_no);
    }

    // Override "hashCode" method here
    public int hashCode() {
        return Integer.parseInt(acc_no);
    }
}

class SavingsAccount extends Account {
    public SavingsAccount(String acc_no, double bal) {
        // Complete the definition
        super(acc_no, bal);
    }

    // Override the toString() method
    public String toString() {
        return "Savings Account:" + acc_no + " , Balance:" + balance;
    }
}

class CurrentAccount extends Account {
    double overdraft_limit;

    public CurrentAccount(String acc_no, double bal, double odl) {
        // Complete the constructor definition}}
        super(acc_no, bal);
        this.overdraft_limit = odl;
    }

    // Override the toString() method}}
    public String toString() {
        return "Current Account:" + acc_no + " , Balance:" + balance;
    }
}

public class Test4 {
    // Define the `accountProcessor' method here
    public static void accountProcessor(ArrayList<Account> unprocessedAccounts) {
        Set<Account> uniqueAccounts = new LinkedHashSet<Account>(unprocessedAccounts);
        Set<Account> sortedAccounts = new TreeSet<Account>(uniqueAccounts);

        for (Account a : sortedAccounts) {
            System.out.println(a);
        }
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        ArrayList<Account> acc = new ArrayList<Account>();

        // reading the number of savings accounts
        int s_acc_count = s.nextInt();
        for (int i = 1; i <= s_acc_count; i++) {
            // reading acc no
            String no = s.next();
            // reading balance
            double bal = s.nextDouble();
            acc.add(new SavingsAccount(no, bal));
        }

        // reading the number of current accounts
        int c_acc_count = s.nextInt();
        for (int i = 1; i <= c_acc_count; i++) {
            // reading acc no
            String no = s.next();
            // reading balance
            double bal = s.nextDouble();
            // reading overdraft limit
            double lim = s.nextDouble();
            acc.add(new CurrentAccount(no, bal, lim));
        }

        accountProcessor(acc);
    }
}