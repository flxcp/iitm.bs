import java.util.Scanner;

class Voter {

    // Please follow the comments and given code segments to complete the program in
    // accordance with the sample outputs.

    // Both the Voter and EVM classes must be created in such a way that they
    // enforce the existence of
    // only a single instance at a time. Each Voter object must be mapped with a
    // unique EVM object and vice versa.
    // A Voter must be allocated an EVM and then the voting process should start,
    // once voting is completed that particular EVM should be freed and the next
    // voter should be called.
    // Again a new EVM must be allocated to the next voter like previously and
    // the process continues till all the voters cast their votes.

    // Define appropriate variables for implementing singleton behaviour
    // in accordance with the given coded parts and sample output
    public static int total_no_of_voters;
    public static Voter new_voter;
    public static int current_voter_count;

    private Voter() {
        // System.out.println("Inside Voter constructor " + "EVm no " + EVM.evm_count +
        // "voterno " + Voter.current_voter_count);
        current_voter_count++;
    }

    public static Voter getVoter() {
        // System.out.println("Inside getVoter " + "EVm no " + EVM.evm_count + "voterno
        // " + Voter.current_voter_count);
        if (new_voter == null) {
            new_voter = new Voter();
            return new_voter;
        } else
            return null;
        // implement singleton behaviour
    }

    public void firstVoter() {
        // System.out.println("Inside firstvoter " + "EVm no " + EVM.evm_count +
        // "voterno " + Voter.current_voter_count);
        if (new_voter != null) {
            EVM new_machine = EVM.getEVM(new_voter);
            new_machine.startVoting();
        }
    }

    public void callNewVoter() {
        // System.out.println("Inside callnewvoter " + "EVm no " + EVM.evm_count +
        // "voterno " + Voter.current_voter_count);
        if (Voter.current_voter_count < Voter.total_no_of_voters) {
            Voter v = Voter.getVoter();
            EVM ev = EVM.getEVM(v);

            // Ignore the following 6 lines of code
            // but do not delete this code in your submission
            // ========================================================================
            try {
                EVM x = EVM.getEVM(null);
                x.startVoting();
                // System.out.println("Inside try");
            } catch (NullPointerException e) {
                System.out.println("EVM is Singleton");
            }
            // ========================================================================
            // Resume writing your code here
            // Hint: Write code to start voting for the new user on the new EVM

            ev.startVoting();
        }
    }
}

class EVM {

    // Define appropriate variables for implementing singleton behaviour
    // in accordance with the given coded parts and sample output
    public static EVM currevm;
    public static Voter current_voter;
    public static int evm_count;

    private EVM(Voter v) {
        // System.out.println("Inside EVM constuctor " + "EVm no " + EVM.evm_count +
        // "voterno " + Voter.current_voter_count);
        current_voter = v;
        evm_count++;
    }

    public static EVM getEVM(Voter v) {
        // System.out.println("Inside getevm " + "EVm no " + EVM.evm_count + "voterno "
        // + Voter.current_voter_count);
        if (currevm == null) {
            currevm = new EVM(v);
            return currevm;
        } else
            return null;
        // Implement singleton behaviour
    }

    public void startVoting() {
        // System.out.println("Inside startvoting " + "EVm no " + EVM.evm_count +
        // "voterno " + Voter.current_voter_count);
        System.out.println("voting under process on EVM number " + EVM.evm_count);
        System.out.println("Voting completed for voter " + Voter.current_voter_count);
        Voter.new_voter = null;
        EVM.currevm = null;

        EVM.current_voter.callNewVoter();
        // Complete voting for the current voter and call the next voter
        // Hint : Use callback here
    }
}

public class Election {
    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        Voter.total_no_of_voters = s.nextInt();
        // Assume input is always non zero
        Voter v = Voter.getVoter();

        // Trying to create another voter when one voter is in the middle of
        // voting process, students can ignore this try-catch block of code

        try {
            Voter x = Voter.getVoter();
            x.callNewVoter();
        } catch (NullPointerException e) {
            System.out.println("Voter is Singleton");
        }

        // Starting the first vote of the day
        v.firstVoter();
    }
}