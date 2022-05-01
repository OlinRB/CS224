public class Edge {
  int capacity;
  int flow;
  boolean backward;
  Node n1;
  Node n2;

  public Edge(Node n1, Node n2, int capacity, boolean backward) {
    this(n1, n2, capacity, 0, backward);
  }

  public Edge(Node n1, Node n2, int capacity, int flow) {
    this.n1 = n1;
    this.n2 = n2;
    this.capacity = capacity;
    this.flow = flow;
    this.backward = false;
  }

  public Edge(Node n1, Node n2, int capacity, int flow, boolean backward) {
    this.n1 = n1;
    this.n2 = n2;
    this.capacity = capacity;
    this.flow = flow;
    this.backward = backward;
  }

  public String toString() {
    String s = n1.name + " -> " + n2.name + " (c=" + capacity + ", f=" + flow + ")";
    return s;
  }
}
