import java.util.ArrayList;
import java.lang.Comparable;

//public class Node implements Comparable {
public class Node {
  ArrayList<Node> adjlistOut;
  ArrayList<Node> adjlistIn;
  int name;
  int numInFromActive;
  boolean active;

  public Node(int name) {
    this.name = name;
    this.adjlistOut = new ArrayList<Node>();
    this.adjlistIn = new ArrayList<Node>();
    this.active = false;
    this.numInFromActive = 0;
  }

  public void addEdge(Node otherNode) {
    // make sure that this edge doesn't already exist
    for (Node n: this.adjlistOut) {
      if (n == otherNode) {
        System.out.println("ERROR: there is already an edge from " + this.name + " to " + otherNode.name);
        return;
      }
    }

    // add edge from this to edge.tail
    this.adjlistOut.add(otherNode);
    // add edge from edge.tail to this
    otherNode.adjlistIn.add(this);
  }

  public String toString() {
    String s = "N" + this.name;
    return s;
  }
}
