// Node class to store node information
public class Nodes {
    public int id;
    public double g; // Actual cost
    public double f; // f(n) = g(n) + h(n)

    // for UCS use
    public Nodes(int id, double g) {
        this.id = id;
        this.g = g;
    }

    // for A* use
    public Nodes(int id, double g, double f) {
        this.id = id;
        this.g = g;
        this.f = f;
    }
}
