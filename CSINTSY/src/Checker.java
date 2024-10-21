// this is a file that intends to check if the scanned records from a given text file is correct


import java.util.Scanner;

public class Checker {
    private static DataRecord recordHolder;
    private static FileReader readFile = new FileReader();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName;
        int userChoice;
        boolean reLoop = false;

        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        // Loops until the user entered an existing and valid file name.
        do {
            System.out.print("Enter filename: ");
            fileName = sc.nextLine();

            System.out.println(); // prints a newline

            // Stores the read graph from a file to a graph variable.
            recordHolder = readFile.fileReader(fileName);
        } while(recordHolder == null);

        while (!reLoop) {
            userChoice = getUserChoice(sc);

            switch (userChoice) {
                case 1: displayHeuristicValues();
                    break;
                case 2: displayCostPath();
                    break;
                case 3: System.out.println("!! EXIT SUCCESSFUL !!");
                    reLoop = true;
                    break;
            }
        }
    }

    public static int getUserChoice(Scanner s) {
        int userChoice;
        System.out.println("!! USER OPTIONS !!");
        System.out.println("(1) Check heuristic values of each node");
        System.out.println("(2) Check connections of a node");
        System.out.println();

        do {
            System.out.println("Enter choice [1/2]: ");
            userChoice = s.nextInt();
        } while(userChoice != 1 && userChoice != 2);

        return userChoice;
    }

    public static void displayHeuristicValues() {
        int ctr = recordHolder.getNumNodes();

        System.out.println("Number of nodes: " + ctr);
        for (int i = 0; i < ctr; i++) {
            System.out.println(recordHolder.getHeuristic().get(i).get(0)); // prints the node
            System.out.print(": " + recordHolder.getHeuristic().get(i).get(1)); // prints the corresponding heuristic value

            System.out.println(); // prints a newline
        }
    }

    public static void displayCostPath() {
        int ctr = recordHolder.getNumConnections();

        System.out.println("Number of connections: " + ctr);
        for (int i = 0; i < ctr; i++) {
            System.out.println(recordHolder.getActual().get(i).get(0)); // prints the label/name of current node
            System.out.println(recordHolder.getActual().get(i).get(1)); // prints the cost of path between current and neighbour
            System.out.println(recordHolder.getActual().get(i).get(2)); // prints the label/name of neighbour/s node

            System.out.println(); // prints a newline
        }
    }
}
