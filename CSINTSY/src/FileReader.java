import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String numNodes;
    private String numNeighbours;
    private String nodeName, heuristicValue;
    private DataRecord recordHolder;
    private String numConnections;

    private String parentNode, costOfPath, neighbourNodeName;

    public DataRecord fileReader(String fileName) {

        try {
            Scanner scanner = new Scanner(new File(fileName));
            numNodes = scanner.nextLine(); // stores the number of nodes

            // Instantiates recordHolder with (n) number of nodes/elements (accounts)
            recordHolder = new DataRecord(Integer.parseInt(numNodes));

            // this loop scans for nodes and their heuristic value
            for (int i = 0; i < Integer.parseInt(numNodes); i++) {
                nodeName = scanner.nextLine(); // stores the name of node
                heuristicValue = scanner.nextLine(); // stores the heuristic value of nodes
                recordHolder.addParentNode(nodeName, heuristicValue); // adds parentnode name and their heuristic value to the list
            }
            numConnections = scanner.nextLine(); // stores the overall number of connections
            for (int i = 0; i < Integer.parseInt(numConnections); i++) {
                parentNode = scanner.nextLine(); // stores the name of the parent nodes
                costOfPath = scanner.nextLine(); // stores the cost of path from parent to neighbour
                neighbourNodeName = scanner.nextLine(); // stores the neighbour name
                recordHolder.addNeighbours(parentNode, costOfPath, neighbourNodeName); // adds the neighbour node name and costOfPath between it and the parent node to the list
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

// EXAMPLE TEXT FILE
22
M 4.1
K 4.1
U 4.5
N 3.7
J1 4.0
J2 4.0
I 4.5
L 4.0
G 4.5
H 4.2
F 4.3
O 4.2
E 4.5
S 4.2
P 4.0
D 4.5
Q 4.5
C 4.0
R 4.3
B 4.2
T 4.6
A 3.9
        60
M <COST OF PATH> N
M <COST OF PATH> K
K <COST OF PATH> J1
K <COST OF PATH> U
K <PUT COP HERE> M
U <PUT COP HERE> K
U <PUT COP HERE> J2
N <COST OF PATH> M
N <COST OF PATH> O
J1 <COST OF PATH> K
J1 <edit cop> J2
J1 <edit cop> I
J2 <edit cop> U
J2 <edit cop> J1
J2 <edit cop> L
I <edit cop> J1
I <edit cop> L
I <edit cop> G
L <edit cop> J2
L <edit cop> I
L <edit cop> H
G <edit cop> I
G <edit cop> H
G <edit cop> F
H <edit cop> L
H <edit cop> G
H <edit cop> F
F <edit cop> G
F <edit cop> H
F <edit cop> E
O <edit cop> N
O <edit cop> E
O <edit cop> S
O <edit cop> P
E <edit cop> F
E <edit cop> O
E <edit cop> D
S <edit cop> O
S <edit cop> P
S <edit cop> Q
P <edit cop> S
P <edit cop> O
P <edit cop> D
P <edit cop> Q
D <edit cop> E
D <edit cop> P
D <edit cop> C
Q <edit cop> S
Q <edit cop> P
Q <edit cop> R
C <edit cop> D
C <edit cop> B
R <edit cop> Q
R <edit cop> B
R <edit cop> T
B <edit cop> C
B <edit cop> R
B <edit cop> A
T <edit cop> R
A <edit cop> B


