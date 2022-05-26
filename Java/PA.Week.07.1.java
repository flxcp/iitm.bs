import java.util.*;

//Define class WrongDestinationException
class WrongDestinationException extends Exception {
    public WrongDestinationException(String message) {
        super(message);
    }
}

// Define class ImproperHeadCountException
class ImproperHeadCountException extends Exception {
    public ImproperHeadCountException(String message) {
        super(message);
    }
}

class CarRental {
    int passenger_count;
    String chosen_destination;
    HashMap<String, Double> available_destinations;

    public CarRental(int pc, String dest) {
        passenger_count = pc;
        chosen_destination = dest;
        // preassigned destinations and total car fare
        // Leave the code below as it is
        available_destinations = new HashMap<String, Double>();
        available_destinations.put("Marina Beach", 2000.0);
        available_destinations.put("Elliot's Beach", 5000.0);
        available_destinations.put("Film City", 8000.0);
    }

    public void carBooker() throws WrongDestinationException, ImproperHeadCountException {
        // define this method according to the problem description

        if (passenger_count <= 0) {
            System.out.println(new ImproperHeadCountException("Head count should be positive non zero value"));
        } else {
            try {
                Double fare = available_destinations.get(chosen_destination);
                System.out.println(
                        "Destination: " + chosen_destination + ", Head cost: " + (fare / passenger_count));
            } catch (NullPointerException e) {
                WrongDestinationException ex = new WrongDestinationException("Invalid destination");
                e.initCause(ex);
                throw e;
            }
        }
    }

}

public class Test4 {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt(); // input the number of car rental requests
        try {
            for (int i = 1; i <= num; i++) {
                int heads = s.nextInt(); // enter head count
                s.nextLine(); // enter destination
                String dest = s.nextLine();
                CarRental obj = new CarRental(heads, dest);
                obj.carBooker();
            }
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
    }
}