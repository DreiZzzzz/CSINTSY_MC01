import java.util.*;
// oct 22

public class DataRecord {
    // <key = node, List()
    private Map<Integer, List<List<Integer>>> connectionsMap;  // To store node connections (parent -> [cost, neighbour])
    private int numNodes;
    private int numConnections;
    private int[] heuristicValues;
    /**
     * The sole constructor of the Graph class
     *
     * @param numNodes an int that holds the total number of nodes
     * @param numConnections an int that holds the total number of connections in the graph
     */
    public DataRecord(int numNodes, int numConnections) {
        this.numNodes = numNodes;
        this.numConnections = numConnections;
        this.connectionsMap = new HashMap<>();
        heuristicValues = new int[numNodes];
        Arrays.fill(heuristicValues, -1); // Initialize all heuristic values to -1
    }

    /**
     * Maps the cost of path and its neighbour to it's connections
     *
     * @param currNode an int that
     * @param costOfPath an int that holds the cost of path between
     * @param neighbourName holds the neighbourName in int form
     */
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

    /**
     *
     *
     * @return
     */
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
        System.out.println("(Main Node) -> [CostPath, Neighbour Node]");
        for (Map.Entry<Integer, List<List<Integer>>> entry : connectionsMap.entrySet()) {
            System.out.print("(" + nodeName[entry.getKey()] + " = " + entry.getKey() + ")" + " -> ");
            for (List<Integer> info : entry.getValue()) {
                temp = info.get(1);
                System.out.print("[" + info.get(0) + ", " + nodeName[temp] + "] "); // Corrected access to List elements
            }
            System.out.println();
        }
        System.out.println(); // newline
    }

    /**
     *
     * @param index
     * @return
     */
    public int getTotalNeighbours(int index) {
        return connectionsMap.get(index).size();
    }

    /**
     * @func that returns the cost of path between two neighbouring nodes, otherwise -1
     */
    public int getCostPath(int mainNode, int neighbourNode) {
        // Loop through all the connections of the mainNode
        List<List<Integer>> connections = connectionsMap.get(mainNode);
        for (List<Integer> connection : connections) {
            // Check if the second element (neighbor) matches neighbourNode
            if (connection.get(1) == neighbourNode) {
                return connection.get(0); // Return the cost (first element)
            }
        }
        return -1; // If no connection to neighbourNode is found
    }

    public void calculateHeuristic(int endNode) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[heuristicValues.length];

        queue.add(endNode);
        visited[endNode] = true;
        heuristicValues[endNode] = 0; // Distance to itself is 0

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            int currentHeuristicValue = heuristicValues[currentNode];

            // Explore neighbors
            List<List<Integer>> neighbors = connectionsMap.get(currentNode);
            if (neighbors != null) {
                for (List<Integer> neighbor : neighbors) {
                    int neighborNode = neighbor.get(1); // Get the neighbor node
                    if (!visited[neighborNode]) {
                        visited[neighborNode] = true;
                        heuristicValues[neighborNode] = currentHeuristicValue + 1; // Increment the distance
                        queue.add(neighborNode);
                    }
                }
            }
        }
    }

    public int getHeuristicValue (int node) {
        return heuristicValues[node];
    }

    public Map<Integer, List<List<Integer>>> getConnectionsMap() {
        return connectionsMap;
    }

}

