import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// oct 22

public class FileReader {
    private int numNodes, numConnections;
    private DataRecord recordHolder;
    private int parentNode, costOfPath, neighbourNodeName;

    public DataRecord fileReader(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            numNodes = scanner.nextInt(); // stores the number of nodes
            numConnections = scanner.nextInt(); // stores the overall number of connections

            // Instantiates recordHolder with the number of nodes and connections
            recordHolder = new DataRecord(numNodes, numConnections);

            // This loop scans for parent-neighbor connections and their costs
            for (int i = 0; i < numConnections; i++) {
                parentNode = scanner.nextInt(); // stores the name of the parent node
                costOfPath = scanner.nextInt(); // stores the cost of the path
                neighbourNodeName = scanner.nextInt(); // stores the neighbor node name
                // Add the connection to the DataRecord
                recordHolder.addNeighbours(parentNode, costOfPath, neighbourNodeName);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("\nFILE DOES NOT EXIST: " + fileName + "\n");
            return null;
        } catch (Exception e) {
            // Handle other potential exceptions like NoSuchElementException, etc.
            System.out.println("Error while reading the file. Please check the file format.");
            return null;
        }

        return recordHolder;
    }
}





