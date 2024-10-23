// Node class to store node information
public class Node {
    public int id;
    public double g; // Actual cost
    public double f; // f(n) = g(n) + h(n)

    public Node(int id, double g, double f) {
        this.id = id;
        this.g = g;
        this.f = f;
    }
}
