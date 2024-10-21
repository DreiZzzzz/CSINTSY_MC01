import java.util.ArrayList;
import java.util.Arrays;

public class DataRecord {
    private ArrayList<ArrayList<String>> heuristic; // holds the parent nodes and their heuristic values
    private ArrayList<ArrayList<String>> actual;
    /**
     * The sole constructor of the Graph class
     *
     * @param numNodes an int that holds the total number of nodes
     */
    public DataRecord(int numNodes) {
        heuristic = new ArrayList<>();
        actual = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            heuristic.add(new ArrayList<>());
            actual.add(new ArrayList<>());
        }
    }

    public void addParentNode(String parentNodeName, String heuristicValue) {
        heuristic.add(new ArrayList<String>(Arrays.asList(parentNodeName, heuristicValue))); // stores node and value per row
    }

    public void addNeighbours(String currNode, String costOfPath, String neighbourName) {
        actual.add(new ArrayList<String>(Arrays.asList(currNode, costOfPath, neighbourName))); // stores currNode, costOfPath, node of neighbour name
    }


}
