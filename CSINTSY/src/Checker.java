import java.io.*;
import java.util.Scanner;

public class Checker {
    private static DataRecord recordHolder;

    // Assuming this is your custom file reader class
    private static FileReader readFile = new FileReader();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName;
        int userChoice;
        int selectAlgo;
        boolean reLoop = false;

        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        // Loops until the user enters an existing and valid file name.
        do {
            System.out.print("Enter filename: ");
            fileName = sc.nextLine();  // Use user input instead of hardcoded "sample.txt"

            System.out.println(); // prints a newline

            // Try reading the file, handle potential exceptions
            recordHolder = readFile.fileReader(fileName);
            if (recordHolder == null) {
                System.out.println("Error reading file. Please try again.");
            }
        } while(recordHolder == null);  // re-loop until the file is read correctly

       // System.out.println("Neighbours of B: " + recordHolder.getActualCost().get(16).size()); // 4
        do {
            userChoice = getUserChoice(sc);
            switch (userChoice) {
                case 1: recordHolder.printHeuristicValue();
                    break;
                case 2: recordHolder.printConnections();
                    break;
                case 3:
                    displayNodes();
                  //   selectAlgo = getEndStart(sc);
                    switch (selectAlgo) {
                        case 1:
                            break;
                        case 2:
                            break;
                    }

                    break;
                case 4: System.out.println("!! PROGRAM EXIT SUCCESSFUL !!");
                    break;
            }


        } while (userChoice != 4);
    }

    public static int getUserChoice(Scanner sc) {
        int userChoice = 0;

        System.out.println("!! USER OPTIONS !!");
        System.out.println("(1) VIEW NODES AND HEURISTIC VALUE");
        System.out.println("(2) VIEW CONNECTIONS AND COST OF PATH");
        System.out.println("(3) TEST ALGORITHM (UCS/A*)");
        System.out.println("(4) EXIT PROGRAM");

        System.out.print("Enter choice [1-3]: ");
        userChoice = sc.nextInt();
        System.out.println(); // newline

        return userChoice;
    }

    public static void displayNodes() {
        int ctr = 0;

        String[] nodeName = { "A", "B", "C",
                              "D", "E", "F",
                              "G", "H", "I",
                              "J1", "J2", "K",
                              "L", "M", "N",
                              "O", "P", "Q",
                              "R", "S", "T",
                              "U"};
        String[] fullName= { "University Mall", "McDonald's", "Perico's", // A-C
                     "Bloemen Hall", "W.H Taft Residence", "EGI TAFT", // D-F
                     "Castro Street", "Agno Food Court", "One Archers'", // G-I
                     "Andrew's La Casita", "Enrique's La Casita", "Green Mall", //J1-K
                     "Green Court", "Sherwood", "Jollibee", //L-N
                     "Dagonoy St.", "Burgundy", "Estrada St.", // O-Q
                     "D' Student's Place", "Leon Guinto St.", "P.Ocampo St.", // R-T
                     "Fidel A. Reyes St."}; // U

        System.out.println("!! ALL NODES !!");
        for (int i = 0; i < nodeName.length; i++) {
            System.out.print(fullName[i] + "(" + nodeName[i] + " = " + i + ")" + "\t\t");
            ++ctr;

            if (ctr == 2) {
                ctr = 0;
                System.out.println(); //newline
            }
        }
        System.out.println(); // newline
    }

    // ask for the algo
    public int getEndStart(Scanner sc) {
        int startNode = 0;
        int endNode = 0;
        boolean isValid = false;
        boolean isNotMatch = false;

        while (!isValid) {
            System.out.println("Enter start node(int): ");
            startNode = sc.nextInt();

            if (startNode >= 0 && startNode <= 21) {
                isValid = true;
            }
        }

        while (!isNotMatch) {
            System.out.println("Enter end node(int): ");
            endNode = sc.nextInt();

            if (endNode >= 0 && endNode <= 21) {
                if (endNode != startNode) {
                    isNotMatch = true;
                }
            }

        }



    }

}

