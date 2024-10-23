import java.util.*;
// last update: oct 22, added costPath to sample.txt

public class Checker {
    private static DataRecord recordHolder;
    private static Scanner sc = new Scanner(System.in);
    private static FileReader readFile = new FileReader();

    public static void main(String[] args) {
        String fileName;
        int userChoice;
        int selectAlgo;
        int startEnd[] = new int[2];

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

        do {
            userChoice = getUserChoice();
            switch (userChoice) {
                case 1: 
                    recordHolder.printConnections();
                    break;
                case 2: 
                    displayNodes();
                    startEnd = getEndStart();

                    // REMOVE ME LATER^ FOR CHECKING ONLY
                    System.out.println("start node:  " + startEnd[0]);
                    System.out.println("end node: " + startEnd[1]);
                    // REMOVE ME LATER^ FOR CHECKING ONLY

                    selectAlgo = userAlgoChoice();
                    switch (selectAlgo) {
                        case 1:
                            AStarSearchAlgo(startEnd[0], startEnd[1]);
                            break;
                        case 2:
                            UCSearchAlgo(startEnd[0], startEnd[1]);
                            break;
                    }

                    break;
                case 3: 
                    getMainNeighbourCost();
                    break;
                case 4: 
                    System.out.println("!! PROGRAM EXIT SUCCESSFUL !!");
                    break;
            }


        } while (userChoice != 4);
    }

    // add do-while checker
    public static int getUserChoice() {
        int userChoice = 0;

        System.out.println("!! USER OPTIONS !!");
        System.out.println("(1) VIEW CONNECTIONS AND COST OF PATH OF ALL NODES");
        System.out.println("(2) TEST ALGORITHM (UCS/A*)");
        System.out.println("(3) VIEW COST OF PATH BETWEEN 2 NODES");
        System.out.println("(4) EXIT PROGRAM");

        do {
            System.out.print("Enter choice [1-4]: ");
            userChoice = sc.nextInt();
            System.out.println(); // new line
        } while(userChoice > 4 || userChoice < 1);

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
        } while (userAlgoChoice > 3 || userAlgoChoice < 1);

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
        recordHolder.calculateHeuristic(endNode);

        System.out.println("Heuristic values (h) from all nodes to end node " + endNode + ":");
        for (int node = 0; node < recordHolder.getNumNodes(); node++) {
            int heuristicValue = recordHolder.getHeuristicValue(node);
            System.out.println("Node " + node + ": h = " + heuristicValue);
        }

        int startHeuristicValue = recordHolder.getHeuristicValue(startNode);
        System.out.println("Heuristic value (h) from start node " + startNode + " to end node " + endNode + " is: " + startHeuristicValue);

        Set<Integer> openSet = new HashSet<>();
        Set<Integer> closedSet = new HashSet<>();
        openSet.add(startNode);

        Map<Integer, Integer> gScore = new HashMap<>();
        Map<Integer, Integer> fScore = new HashMap<>();
        Map<Integer, Integer> cameFrom = new HashMap<>();

        gScore.put(startNode, 0);
        fScore.put(startNode, recordHolder.getHeuristicValue(startNode)); // f = g + h

        while (!openSet.isEmpty()) {
            // Find the node in open set with the lowest fScore
            int currentNode = findLowestFScoreNode(openSet, fScore);
    
            // If we reached the end node, reconstruct the path
            if (currentNode == endNode) {
                reconstructPath(cameFrom, currentNode);
                return; // Exit the method after reconstructing the path
            }
    
            // Move the current node from open to closed
            openSet.remove(currentNode);
            closedSet.add(currentNode);
    
            // Process each neighbor of the current node
            for (List<Integer> neighborData : recordHolder.getConnectionsMap().get(currentNode)) {
                int neighborNode = neighborData.get(1); // Neighbor node
                int edgeCost = neighborData.get(0); // Cost to reach this neighbor
    
                if (closedSet.contains(neighborNode)) {
                    continue; // Ignore the neighbor which is already evaluated
                }
    
                // Tentative g score for the neighbor
                int tentativeGScore = gScore.get(currentNode) + edgeCost;
    
                // If the neighbor is not in openSet, add it
                if (!openSet.contains(neighborNode)) {
                    openSet.add(neighborNode);
                } else if (tentativeGScore >= gScore.getOrDefault(neighborNode, Integer.MAX_VALUE)) {
                    continue; // This is not a better path
                }
    
                // This path is the best so far, record it
                cameFrom.put(neighborNode, currentNode);
                gScore.put(neighborNode, tentativeGScore);
                fScore.put(neighborNode, tentativeGScore + recordHolder.getHeuristicValue(neighborNode));
            }
        }

        System.out.println("No path found from node " + startNode + " to node " + endNode);
    }

    private static int findLowestFScoreNode(Set<Integer> openSet, Map<Integer, Integer> fScore) {
        int lowestNode = -1;
        int lowestScore = Integer.MAX_VALUE;
    
        for (int node : openSet) {
            int score = fScore.getOrDefault(node, Integer.MAX_VALUE);
            if (score < lowestScore) {
                lowestScore = score;
                lowestNode = node;
            }
        }
        return lowestNode;
    }

    private static void reconstructPath(Map<Integer, Integer> cameFrom, int currentNode) {
        List<Integer> totalPath = new ArrayList<>();
        totalPath.add(currentNode);
        
        while (cameFrom.containsKey(currentNode)) {
            currentNode = cameFrom.get(currentNode);
            totalPath.add(currentNode);
        }
    
        // Print the path from start to end
        Collections.reverse(totalPath); // Reverse the path to get it from start to end
        System.out.println("Path found: " + totalPath);
    }

    // IMPLEMENT FIRST
    public static void UCSearchAlgo(int startNode, int endNode) {

    }

    public static void getMainNeighbourCost() {
        // REMOVE ME AFTER TESTING
        int mainNode = -1;
        int neighbourNode = -1;
        int costOfPath = -1;
        String[] nodeName = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J1",
                "J2", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
                "T", "U"
        };

        do {
            System.out.print("Enter main node: ");
            mainNode = sc.nextInt();
            System.out.println();
        } while (mainNode > 21 || mainNode < 0);

        do {
            System.out.print("Enter neighbour node: ");
            neighbourNode = sc.nextInt();
            System.out.println();
        } while (neighbourNode != mainNode && neighbourNode > 21 && neighbourNode < 0);

        costOfPath = recordHolder.getCostPath(mainNode, neighbourNode);

        System.out.println("COP FROM " + nodeName[mainNode] + " TO " +  nodeName[neighbourNode] + " = " + costOfPath);
        System.out.println("COP FROM " + mainNode + " TO " + neighbourNode + " = " + costOfPath);
    }

    //
    /*
    public boolean getNodeIndex(int nodeKey) {

    }

     */






}

