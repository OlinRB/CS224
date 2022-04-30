import java.util.ArrayList;

public class Node {
  int name;
  ArrayList<Edge> adjlist;
  ArrayList<Edge> adjlistResid;

  public Node(int name) {
    this.name = name;
    this.adjlist = new ArrayList<Edge>();
    this.adjlistResid = new ArrayList<Edge>();
  }

  public void add(Edge edge) {
    this.adjlist.add(edge);
  }

  public void addResidualEdge(Edge edge) {
    this.adjlistResid.add(edge);
  }
}
