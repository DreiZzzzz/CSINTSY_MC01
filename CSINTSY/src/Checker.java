import java.util.Scanner;

public class Checker {
    private static DataRecord recordHolder;
    private static Scanner sc = new Scanner(System.in);
    private static FileReader readFile = new FileReader();

    public static void main(String[] args) {
        String fileName;
        int userChoice;
        int selectAlgo;
        int startEnd[] = new int[2];
        boolean reLoop = false;
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

        printAst();
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

       // System.out.println("Neighbours of B: " + recordHolder.getActualCost().get(16).size()); // 4 (remove mamaya)
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
                    displayNodes(nodeName, fullName);
                    startEnd = getEndStart();

                    // REMOVE ME LATER^ FOR CHECKING ONLY
                    System.out.println("start node:  " + startEnd[0]);
                    System.out.println("end node: " + startEnd[1]);
                    // REMOVE ME LATER^ FOR CHECKING ONLY

                    selectAlgo = userAlgoChoice();
                    switch (selectAlgo) {
                        case 1: AStarSearchAlgo(startEnd[0], startEnd[1], nodeName, fullName);
                            break;
                        case 2: // implement UCS
                            break;
                    }
                    break;
                case 4: printAst();
                    System.out.println("!! PROGRAM EXIT SUCCESSFUL !!");
                    break;
            }
        } while (userChoice != 4);
    }

    public static int getUserChoice() {
        int userChoice = 0;

        printAst();
        System.out.println("!! USER OPTIONS !!");
        System.out.println("(1) VIEW NODES AND HEURISTIC VALUE");
        System.out.println("(2) VIEW CONNECTIONS AND COST OF PATH OF ALL NODES");
        System.out.println("(3) TEST ALGORITHM (UCS/A*)");
        System.out.println("(4) EXIT PROGRAM\n");

        do {
            System.out.print("Enter choice [1-4]: ");
            userChoice = sc.nextInt();
            System.out.println(); // new line
        } while(userChoice > 4 && userChoice < 1);

        return userChoice;
    }

    public static int userAlgoChoice() {
        int userAlgoChoice = 0;

        printAst();
        System.out.println("!! ALGORITHM OPTIONS !!");
        System.out.println("(1) A* SEARCH ALGORITHM");
        System.out.println("(2) UCS SEARCH ALGORITHM");
        System.out.println("(3) BACK TO MAIN MENU\n");

        do {
            System.out.print("Enter choice (int): ");
            userAlgoChoice = sc.nextInt();
            System.out.println(); // newline
        } while (userAlgoChoice > 3 && userAlgoChoice < 1);

        return userAlgoChoice;
    }

    //
    public static void displayNodes(String[] nodeName, String[] fullName) {
        System.out.println("!! DLSU FOOD NODES !!");
        for (int i = 0; i < nodeName.length; i++) {
            System.out.println(fullName[i] + "(" + nodeName[i] + " = " + i + ")");
        }
        System.out.println(); // newline
    }

    // function that gets the start and nodes from the user input
    public static int[] getEndStart() {
        int[] startEnd  = new int[2];
        boolean isValid = false;
        boolean isNotMatch = false;

        while (!isValid) {
            System.out.print("Enter start node(int): ");
            startEnd[0] = sc.nextInt();
            System.out.println(); // newline

            if (startEnd[0] >= 0 && startEnd[0] <= 21) {
                isValid = true;
            }
        }

        while (!isNotMatch) {
            System.out.print("Enter end node(int): ");
            startEnd[1] = sc.nextInt();
            System.out.println(); // newline

            if (startEnd[1] >= 0 && startEnd[1] <= 21) {
                if (startEnd[0] != startEnd[1]) {
                    isNotMatch = true;
                }
            }
        }

        return startEnd;
    }

    // FURTHER CHECK IF RESULTS ARE RIGHT
    public static void AStarSearchAlgo(int startNode, int endNode, String[] nodeName, String[] fullName) {
        AStarSearchAlgorithm aStar = new AStarSearchAlgorithm(recordHolder, startNode, endNode, nodeName, fullName);
        aStar.performAlgo();
    }

    // IMPLEMENT FIRST
    public static void UCSearchAlgo(int startNode, int endNode) {
        /*code here */
        // uniformCS.performAlgo();
    }

    public static void printAst() {
        System.out.println("**************************************************************************************************************************************************************************************************");
    }
}

