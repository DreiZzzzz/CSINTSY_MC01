import java.util.*;

public class DataRecord {
    // Map<Node, hValue>
    private Map<Integer, Double> parentNodeHeuristicMap; // To store node and its heuristic value
    private Map<Integer, List<List<Integer>>> connectionsMap;  // To store node connections (parent -> [cost, neighbour])

    private int numNodes;
    private int numConnections;
    /**
     * The sole constructor of the Graph class
     *
     * @param numNodes an int that holds the total number of nodes
     */
    public DataRecord(int numNodes, int numConnections) {
        this.numNodes = numNodes;
        this.numConnections = numConnections;
        this.parentNodeHeuristicMap = new HashMap<>();
        this.connectionsMap = new HashMap<>();
    }

    public void addParentNode(int parentNodeLabel, double heuristicValue) {
        this.parentNodeHeuristicMap.put(parentNodeLabel, heuristicValue);
    }

    public void addNeighbours(int currNode, int costOfPath, int neighbourName) {
        // Create a new ArrayList if there is no existing list for currNode
        this.connectionsMap.putIfAbsent(currNode, new ArrayList<>());


        // Create a new list for cost and neighbour
        List<Integer> neighbourInfo = new ArrayList<>();
        neighbourInfo.add(costOfPath);
        neighbourInfo.add(neighbourName);

        // Add the new information to the existing list for currNode
        this.connectionsMap.get(currNode).add(neighbourInfo);
    }

    public Map<Integer, Double> getHeuristic() {
        return this.parentNodeHeuristicMap;
    }

    public Map<Integer, List<List<Integer>>> getActualCost(){
        return this.connectionsMap;
    }

    public int getNumNodes() {
        return this.numNodes;
    }

    public int getNumConnections() {
        return this.numConnections;
    }

    public void printConnections() {
        int temp = 0;
        String[] nodeName = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J1",
                "J2", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
                "T", "U"
        };
                                            // ( get.(0), get.(1) )
        System.out.println("(Current Node) -> [CostPath, Neighbour Node]");
        for (Map.Entry<Integer, List<List<Integer>>> entry : connectionsMap.entrySet()) {
            System.out.print("(" + nodeName[entry.getKey()] + " = " + entry.getKey() + ")" + " -> ");
            for (List<Integer> info : entry.getValue()) {
                temp = info.get(1);
                System.out.print("[" + info.get(0) + ", " + nodeName[temp] + "] "); // Corrected access to List elements
            }
            System.out.println();
        }
    }
}

