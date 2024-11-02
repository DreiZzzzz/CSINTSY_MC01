// last edit: Nov 2, 2024 @ 11:20 p.m

import java.util.Scanner;

public class Main {
    private static DataRecord recordHolder;
    private static Scanner sc = new Scanner(System.in);
    private static FileReader readFile = new FileReader();

    public static void main(String[] args){
        String fileName;
        int userChoice = 0;
        int startEnd[] = new int[2];
        int selectedAlgo = 0;
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

        // Loops until the user enters an existing and valid file name.
        do {
            System.out.print("Enter filename: ");
            fileName = sc.nextLine();
            System.out.println(); // prints a newline

            recordHolder = readFile.fileReader(fileName);
            if (recordHolder == null) {
                System.out.println("Error reading file. Please try again.");
                printAst();
            }
        } while(recordHolder == null);  // re-loop until the file is read correctly

        printAst();
        do {
            displayNodes(nodeName, fullName);
            System.out.println("NOTE: Enter valid values for start and end nodes.\nOtherwise, enter -1 for end node value to quit. \n");

            startEnd = getEndStart();

            // function call for heuristic

            if (startEnd[1] == -1) {
                break; // exit loop and will not execute the remaining codes below
            }

            recordHolder.calculateHeuristic(startEnd[1]);

            do {
                userChoice = getUserChoice();

                switch (userChoice) {
                    case 1: printAst();
                        recordHolder.printHeuristicValue(nodeName);
                        break;
                    case 2: printAst();
                        recordHolder.printConnections(nodeName);
                        break;
                    case 3: printAst();
                        do {
                            selectedAlgo = userAlgoChoice();

                            switch (selectedAlgo) {
                                case 1:
                                    AStarSearchAlgo(startEnd[0], startEnd[1], nodeName, fullName);
                                    break;
                                case 2:
                                    UCSearchAlgo(startEnd[0], startEnd[1], nodeName, fullName);
                                    break;
                            }
                        } while (selectedAlgo != 3);
                        break;
                }

            } while(userChoice != 0);

        } while (startEnd[0] != -1 && startEnd[1] != -1);

        System.out.println("!!! END !!!");
    }

    public static int getUserChoice() {
        int userChoice = 0;

        printAst();
        System.out.println("!! USER OPTIONS !!");
        System.out.println("(0) CHANGE START AND GOAL NODES");
        System.out.println("(1) VIEW NODES AND HEURISTIC VALUE");
        System.out.println("(2) VIEW CONNECTIONS AND COST OF PATH BETWEEN NODES");
        System.out.println("(3) TEST ALGORITHM (UCS/A*) \n");

        do {
            System.out.print("Enter choice [0-3]: ");
            userChoice = sc.nextInt();
            System.out.println(); // new line
        } while(userChoice > 3 && userChoice < 1);

        return userChoice;
    }

    public static int[] getEndStart() {
        int[] startEnd  = new int[2];
        boolean isValid = false;
        boolean isNotMatch = false;

        while (!isValid) {
            System.out.print("Enter start node (int): ");
            startEnd[0] = sc.nextInt();
            System.out.println(); // newline

            if (startEnd[0] >= 0 && startEnd[0] <= 21) {
                isValid = true;
            }
        }

        while (!isNotMatch) {
            System.out.print("Enter end node (int): ");
            startEnd[1] = sc.nextInt();
            System.out.println(); // newline

            if (startEnd[1] >= -1 && startEnd[1] <= 21) {
                if (startEnd[1] == -1) {
                    break;
                }
                if (startEnd[0] != startEnd[1]) {
                    isNotMatch = true;
                }
            }
        }

        return startEnd;
    }

    public static void AStarSearchAlgo(int startNode, int endNode, String[] nodeName, String[] fullName) {
        AStarSearchAlgorithm aStar = new AStarSearchAlgorithm(recordHolder, startNode, endNode, nodeName, fullName);
        aStar.performAlgo();
    }

    public static void UCSearchAlgo(int startNode, int endNode, String[] nodeName, String[] fullName) {
        UniformCostSearchAlgorithm ucs = new UniformCostSearchAlgorithm(recordHolder, startNode, endNode, nodeName, fullName);
        ucs.performAlgo();
    }

    public static void displayNodes(String[] nodeName, String[] fullName) {
        System.out.println("!! DLSU FOOD NODES !!");
        for (int i = 0; i < nodeName.length; i++) {
            System.out.println(fullName[i] + "(" + nodeName[i] + " = " + i + ")");
        }
        System.out.println(); // newline
    }

    public static int userAlgoChoice() {
        int userAlgoChoice = 0;

        System.out.println("!! ALGORITHM OPTIONS !!");
        System.out.println("(1) A* SEARCH ALGORITHM");
        System.out.println("(2) UCS SEARCH ALGORITHM");
        System.out.println("(3) BACK TO USER OPTIONS\n");

        do {
            System.out.print("Enter choice (int): ");
            userAlgoChoice = sc.nextInt();
            System.out.println(); // newline
        } while (userAlgoChoice > 3 && userAlgoChoice < 1);

        return userAlgoChoice;
    }

    public static void printAst() {
        System.out.println("**************************************************************************************************************************************************************************************************");
    }
}

