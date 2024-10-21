import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String numNodes;
    private String numNeighbours;
    private String nodeName, heuristicValue;
    private DataRecord recordHolder;

    public DataRecord fileReader(String fileName) {

        try {
            Scanner scanner = new Scanner(new File(fileName));
            numNodes = scanner.nextLine(); // stores the number of nodes

            // this loop scans for nodes and their heuristic value
            for (int i = 0; i < Integer.parseInt(numNodes); i++) {
                nodeName = scanner.nextLine(); // stores the name of node
                heuristicValue = scanner.nextLine(); // stores the heuristic value of nodes
                recordHolder.addParentNode(nodeName, heuristicValue);
            }

            // this loop scans for the neighbours of each node and the cost of path
            for (int i = 0; i < Integer.parseInt(numNodes); i++) {
                numNeighbours = scanner.nextLine(); // stores the number of neighbours of each node per loop

                for (int j = 0; j < Integer.parseInt(numNeighbours); j++) {
                    // add here
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // Display a message when the input file name does not exist
            System.out.println("\nFILE DOES NOT EXIST\n");
            return null;
        }

        return recordHolder;
    }
}
