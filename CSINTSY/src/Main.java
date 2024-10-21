import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileToRead;
        String startNode, goalNode;


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
    }
}