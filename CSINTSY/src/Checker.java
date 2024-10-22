import java.util.Scanner;
// last update: oct 22

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
            userChoice = getUserChoice();
            switch (userChoice) {
                case 1: recordHolder.printHeuristicValue();
                    break;
                case 2: recordHolder.printConnections();
                    break;
                case 3: displayNodes();
                    startEnd = getEndStart();

                    // REMOVE ME LATER^ FOR CHECKING ONLY
                    System.out.println("start node:  " + startEnd[0]);
                    System.out.println("end node: " + startEnd[1]);
                    // REMOVE ME LATER^ FOR CHECKING ONLY

                    selectAlgo = userAlgoChoice();
                    switch (selectAlgo) {
                        case 1:
                            break;
                        case 2:
                            break;
                    }

                    break;
                case 4: getMainNeighbourCost();
                case 5: System.out.println("!! PROGRAM EXIT SUCCESSFUL !!");
                    break;
            }


        } while (userChoice != 5);
    }

    // add do-while checker
    public static int getUserChoice() {
        int userChoice = 0;

        System.out.println("!! USER OPTIONS !!");
        System.out.println("(1) VIEW NODES AND HEURISTIC VALUE");
        System.out.println("(2) VIEW CONNECTIONS AND COST OF PATH OF ALL NODES");
        System.out.println("(3) TEST ALGORITHM (UCS/A*)");
        System.out.println("(4) VIEW COST OF PATH BETWEEN 2 NODES");
        System.out.println("(5) EXIT PROGRAM");

        do {
            System.out.print("Enter choice [1-4]: ");
            userChoice = sc.nextInt();
            System.out.println(); // new line
        } while(userChoice > 4 && userChoice < 1);

        return userChoice;
    }

        // add do while classes
    public static int userAlgoChoice() {
        int userAlgoChoice = 0;

        System.out.println("!! ALGORITHM OPTIONS !!");
        System.out.println("(1) A* SEARCH ALGORITHM");
        System.out.println("(2) UCS SEARCH ALGORITHM");
        System.out.println("(3) BACK TO MAIN MENU");

        do {
            System.out.print("Enter choice (int): ");
            userAlgoChoice = sc.nextInt();
            System.out.println(); // newline
        } while (userAlgoChoice > 3 && userAlgoChoice < 1);

        return userAlgoChoice;
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

        System.out.println("!! DLSU FOOD NODES !!");
        for (int i = 0; i < nodeName.length; i++) {
            System.out.print(fullName[i] + "(" + nodeName[i] + " = " + i + ")" + "\t\t");
            ++ctr;

            if (ctr == 4) {
                ctr = 0;
                System.out.println(); //newline
            }
        }
        System.out.println(); // newline
        System.out.println(); // newline
    }

    // ask for the algo

    /**
     *
     * @param
     * @return
     */
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

    // IMPLEMENT FIRST
    // A* search => continue with the lowest path
    // COP + heuristic of current node
    public static void AStarSearchAlgo(int startNode, int endNode) {
        // recordHolder.ge
    }

    // IMPLEMENT FIRST
    public static void UCSearchAlgo(int startNode, int endNode) {

    }

    public static void getMainNeighbourCost() {
        // REMOVE ME AFTER TESTING
        int mainNode = -1;
        int neighbourNode = -1;
        int costOfPath = -1;

        do {
            System.out.print("Enter main node: ");
            mainNode = sc.nextInt();
            System.out.println();
        } while (mainNode > 21 && mainNode < 0);

        do {
            System.out.print("Enter neighbour node: ");
            neighbourNode = sc.nextInt();
            System.out.println();
        } while (neighbourNode != mainNode && neighbourNode > 21 && neighbourNode < 0);

        costOfPath = recordHolder.getCostPath(mainNode, neighbourNode);

        System.out.println("COP FROM " + mainNode + " TO " + neighbourNode + " = " + costOfPath);
    }

    /*
    public boolean getNodeIndex(int nodeKey // arrlist) {

    }
    */





}

