import java.util.*;

class CricketPlayer {
    private String name;
    private int wickets;
    private int runs;
    private int matches;

    public CricketPlayer(String s, int w, int r, int m) {
        this.name = s;
        this.wickets = w;
        this.runs = r;
        this.matches = m;
    }

    public String getName() {
        return name;
    }

    public int getWickets() {
        return wickets;
    }

    public int getRuns() {
        return runs;
    }

    public double avgRuns() {
        return runs / matches;
    }

    public double avgWickets() {
        return wickets / matches;
    }
}

public class Main {
    public static void displayPlayers(ArrayList<CricketPlayer> bw, ArrayList<CricketPlayer> bt) {

        for (CricketPlayer bowlers : bw) {
            System.out.print(bowlers.getName() + " ");
        }
        System.out.println();
        for (CricketPlayer batsman : bt) {
            System.out.print(batsman.getName() + " ");
        }
    }

    public static void main(String[] args) {
        ArrayList<CricketPlayer> players = new ArrayList<CricketPlayer>();

        Scanner sc = new Scanner(System.in);
        CricketPlayer p1 = new CricketPlayer(sc.next(), sc.nextInt(),
                sc.nextInt(), sc.nextInt());
        players.add(p1);
        CricketPlayer p2 = new CricketPlayer(sc.next(), sc.nextInt(),
                sc.nextInt(), sc.nextInt());
        players.add(p2);
        CricketPlayer p3 = new CricketPlayer(sc.next(), sc.nextInt(),
                sc.nextInt(), sc.nextInt());
        players.add(p3);
        CricketPlayer p4 = new CricketPlayer(sc.next(), sc.nextInt(),
                sc.nextInt(), sc.nextInt());
        players.add(p4);
        sc.close();

        ArrayList<CricketPlayer> bw = new ArrayList<CricketPlayer>();
        ArrayList<CricketPlayer> bt = new ArrayList<CricketPlayer>();

        for (CricketPlayer p : players) {
            if (p.avgRuns() > 25) {
                bt.add(p);
            }
            if (p.avgWickets() > 1) {
                bw.add(p);
            }
        }

        displayPlayers(bw, bt);
    }
}