import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private String numNodes;
    private String nodeName, heuristicValue;
    private DataRecord recordHolder;
    private String numConnections;
    private String parentNode, costOfPath, neighbourNodeName;

    public DataRecord fileReader(String fileName) {

        try {
            Scanner scanner = new Scanner(new File(fileName));
            numNodes = scanner.nextLine(); // stores the number of nodes
            numConnections = scanner.nextLine(); // stores the overall number of connections

            // Instantiates recordHolder with (n) number of nodes/elements (accounts)
            recordHolder = new DataRecord(numNodes, numConnections); // assume string will be converted to int in the class

            // this loop scans for nodes and their heuristic value
            for (int i = 0; i < Integer.parseInt(numNodes); i++) {
                nodeName = scanner.nextLine(); // stores the name of node
                heuristicValue = scanner.nextLine(); // stores the heuristic value of nodes
                recordHolder.addParentNode(nodeName, heuristicValue); // adds parent node name and their heuristic value to the list
            }
            for (int i = 0; i < Integer.parseInt(numConnections); i++) {
                parentNode = scanner.nextLine(); // stores the name of the parent nodes
                costOfPath = scanner.nextLine(); // stores the cost of path from parent to neighbour
                neighbourNodeName = scanner.nextLine(); // stores the neighbour name
                recordHolder.addNeighbours(parentNode, costOfPath, neighbourNodeName); // adds the neighbour node name and costOfPath between it and the parent node to the list
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // Display a message when the input file name does not exist
            System.out.println("\nFILE DOES NOT EXIST: " + fileName + "\n");
            return null;
        }

        return recordHolder;
    }
}

// text file
22 60
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
M 1 N
M 2 K
K 3 J1
K 4 U
K 5 M
U 6 K
U 7 J2
N 8 M
N 9 O
J1 10 K
J1 11 J2
J1 12 I
J2 13 U
J2 14 J1
J2 15 L
I 16 J1
I 17 L
I 18 G
L 19 J2
L 20 I
L 21 H
G 22 I
G 23 H
G 24 F
H 25 L
H 26 G
H 27 F
F 28 G
F 29 H
F 30 E
O 31 N
O 32 E
O 33 S
O 34 P
E 35 F
E 36 O
E 37 D
S 38 O
S 39 P
S 40 Q
P 41 S
P 42 O
P 43 D
P 44 Q
D 45 E
D 46 P
D 47 C
Q 48 S
Q 49 P
Q 50 R
C 51 D
C 52 B
R 53 Q
R 54 B
R 55 T
B 56 C
B 57 R
B 58 A
T 59 R
A 60 B





