import java.util.Scanner;

// New Main file is Checker.java
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileToRead;
        String startNode, goalNode;
        String userChoice;

        // asks the user to enter the file name where the records are stored
        System.out.println("Enter file to read: ");
        fileToRead = sc.nextLine();

        // display all nodes

        // asks the user to enter the starting point
        System.out.println("Enter Start: ");
        startNode = sc.nextLine();

        // asks the user to enter the goal node
        System.out.println("Enter Goal");
        goalNode = sc.nextLine();

        // asks the user on what algorithm to use
        // loop while input is not valid
        do {
            System.out.println("!! USER OPTIONS !!");
            System.out.println("(UCS) Uniform Cost Search Algorithm");
            System.out.println("(A*) A* Search Algorithm");

            System.out.println("Enter choice [UCS/A*]: ");
            userChoice = sc.nextLine();
        } while(!userChoice.equals("UCS") && !userChoice.equals("A*"));


        System.out.println( );
    }
}