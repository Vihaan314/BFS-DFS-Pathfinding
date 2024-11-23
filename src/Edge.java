public class Edge {
    public int from;
    public int to;

    public Edge(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "{" + from + ", " + to + "}";
    }
}
