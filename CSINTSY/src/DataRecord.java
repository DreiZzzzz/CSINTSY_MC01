import java.util.ArrayList;
import java.util.Arrays;

public class DataRecord {
    private ArrayList<ArrayList<String>> records; // 2D arraylist

    /**
     * The sole constructor of the Graph class
     *
     * @param numNodes an int that holds the total number of nodes
     */
    public DataRecord(int numNodes) {
        records = new ArrayList<>();

        for (int i = 0; i < numNodes; i++) {
            records.add(new ArrayList<>());
        }
    }

    public void addParentNode(String parentNodeName, String heuristicValue) {
        records.add(new ArrayList<String>(Arrays.asList(parentNodeName, heuristicValue))); // stores node and value per row
    }




    public void addNeighbours() {

    }
}
