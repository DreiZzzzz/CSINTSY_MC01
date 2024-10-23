import java.util.*;

public class DataRecord {
    private Map<Integer, Double> parentNodeHeuristicMap; // To store node and its heuristic value (key = node -> hValue)
    private Map<Integer, List<List<Integer>>> connectionsMap;  // To store node connections (parent -> [cost, neighbour])
    private int numNodes;
    private int numConnections;

    public DataRecord(int numNodes, int numConnections) {
        this.numNodes = numNodes;
        this.numConnections = numConnections;
        this.parentNodeHeuristicMap = new HashMap<>();
        this.connectionsMap = new HashMap<>();
    }

    // Adds a node, and it's corresponding heuristic value using maps.
    public void addParentNode(int parentNodeLabel, double heuristicValue) {
        this.parentNodeHeuristicMap.put(parentNodeLabel, heuristicValue);
    }

    // Maps the cost of path and its neighbour to it's connections
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

    public void printConnections(String[] nodeName) {
        int temp = 0;
        System.out.println("(Main Node) -> [CostPath, Neighbour Node]"); // ( get.(0), get.(1) )
        for (Map.Entry<Integer, List<List<Integer>>> entry : connectionsMap.entrySet()) {
            System.out.print("(" + nodeName[entry.getKey()] + " = " + entry.getKey() + ")" + " -> ");
            for (List<Integer> info : entry.getValue()) {
                temp = info.get(1);
                System.out.print("[" + info.get(0) + ", " + nodeName[temp] + "] "); // Corrected access to List elements
            }
            System.out.println(); // newline
        }
        System.out.println(); // newline
    }

    public void printHeuristicValue(String[] nodeName) {
        System.out.println("<NODE, HEURISTIC VALUE>");
        for (Map.Entry<Integer, Double> entry : parentNodeHeuristicMap.entrySet()) {
            Integer key = entry.getKey();
            Double value = entry.getValue();
            System.out.println("Node: (" + key + " = " + nodeName[key] + "), H(n): " + value);
        }
        System.out.println();
    }
}

