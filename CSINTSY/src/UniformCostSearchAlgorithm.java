import java.util.*;

public class UniformCostSearchAlgorithm {
    private int goal, start;
    private Map<Integer, List<List<Integer>>> connectionsMap;
    private String[] nodeName;
    private String[] fullName;

    public UniformCostSearchAlgorithm(DataRecord recordHolder, int startNode, int endNode, String[] nodeName, String[] fullName) {
        this.connectionsMap = recordHolder.getActualCost();
        this.start = startNode;
        this.goal = endNode;
        this.nodeName = nodeName;
        this.fullName = fullName;
    }

    public void performAlgo() { // change return type from List<Integer> to void
        PriorityQueue<Nodes> openSet = new PriorityQueue<>(Comparator.comparingDouble(n -> n.g)); // Priority queue prioritizing nodes by cost g(n)
        Map<Integer, Double> gScore = new HashMap<>(); // Map to store the cost of the best path to each node
        Set<Integer> closedSet = new HashSet<>();   // Set to keep track of visited nodes
        Map<Integer, Integer> cameFrom = new HashMap<>(); // Map to store the optimal path

        // Initialize
        gScore.put(start, 0.0);
        openSet.add(new Nodes(start, 0.0));

        printAst();
        System.out.println("Starting Uniform Cost Search algorithm traversal:");
        while (!openSet.isEmpty()) {
            Nodes current = openSet.poll();
            System.out.println("Exploring node: (" + nodeName[current.id] + " == " + current.id + ") " + fullName[current.id]);  // Print the node currently being explored

            if (current.id == goal) {
                printAst();
                System.out.println("!! GOAL REACHED!!");
                double totalCost = gScore.get(goal); // Total cost of the path
                System.out.println("Total Actual Cost: " + totalCost);
                List<Integer> path = reconstructPath(cameFrom, current.id); // Reconstruct the path
                System.out.print("Whole Path: ");

                for (int i = 0; i < path.size(); i++) {
                    System.out.print("(" + path.get(i) + " == " + nodeName[path.get(i)] +") " + fullName[path.get(i)]);
                    if (i == path.size() - 1) {
                        System.out.println(); // newline
                        continue;
                    }
                    System.out.print(" => ");
                }
                System.out.println(); // newline
                break; // to prevent unnecessary computations
            }
            closedSet.add(current.id);

            // Explore neighbors
            for (List<Integer> neighborData : connectionsMap.getOrDefault(current.id, new ArrayList<>())) {
                int neighbor = neighborData.get(1);
                double cost = neighborData.get(0);

                if (closedSet.contains(neighbor)) continue;

                double tentativeGScore = gScore.getOrDefault(current.id, Double.MAX_VALUE) + cost;

                if (tentativeGScore < gScore.getOrDefault(neighbor, Double.MAX_VALUE)) {
                    cameFrom.put(neighbor, current.id);   // Update the best path to neighbor
                    gScore.put(neighbor, tentativeGScore);
                    openSet.add(new Nodes(neighbor, tentativeGScore));

                    // Print neighbor => for checking only remove / EDIT ME LATER
                    System.out.println("  Unvisited Neighbour: (" + nodeName[neighbor] + " == " + fullName[neighbor] + " == " + neighbor + ")" + ", gScore: " + tentativeGScore);
                    System.out.println(); // newline
                }
            }
        }
    }

    // Reconstructs the path after reaching the goal and returns the reconstructed path
    private List<Integer> reconstructPath(Map<Integer, Integer> cameFrom, int current) {
        List<Integer> path = new ArrayList<>();
        while (cameFrom.containsKey(current)) {
            path.add(current);
            current = cameFrom.get(current);
        }
        path.add(current); // Add the start node at the end
        Collections.reverse(path); // Reverse to get the path from start to goal
        return path;
    }

    public static void printAst() {
        System.out.println("**************************************************************************************************************************************************************************************************");
    }
}

