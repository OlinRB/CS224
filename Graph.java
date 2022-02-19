import java.util.ArrayList;

public class Graph {
  ArrayList<Node> nodes;

  public Graph() {
    this.nodes = new ArrayList<Node>();
  }

  public void addNode(Node n) {
    this.nodes.add(n);
  }

  public void addEdge(Node n1, Node n2) {
    // outgoing edge
    int idx1 = this.nodes.indexOf(n1);
    if (idx1 < 0) {
      System.out.println("node " + n1.name + " not found in graph");
      return;
    }

    // incoming edge
    int idx2 = this.nodes.indexOf(n2);
    if (idx2 < 0) {
      System.out.println("node " + n2.name + " not found in graph");
      return;
    }

    n1.addEdge(n2);
  }

  public void print() {
    for (Node n: this.nodes) {
      System.out.print("Node " + n.name + ":");
      for (Node n2: n.adjlistOut)
        System.out.print(" " + n2.name);
      System.out.print(" |");
      for (Node n2: n.adjlistIn)
        System.out.print(" " + n2.name);
      System.out.println();
    }
  }

  public boolean topoOrder() {
    int i, startingNodeIndex;
    // Print each iteration
    for (i = 0; i < nodes.size(); ++i) {
      // Print out results of edges from graph
      System.out.print("node ");
      System.out.print(nodes.get(i).toString());
      System.out.print(": #incoming edges from active nodes = ");
      System.out.println(nodes.get(i).numInFromActive);
      // Set all nodes to active that remain in the graph
    }
    // Base case for recursion
    if (nodes.size() == 0)
      return true;

    // Find starting node(s)
    for (i = 0; i < nodes.size(); ++i) {
      // Set number of incoming edges from active nodes
      int cnt = 0;
      for (int j = 0; j < nodes.get(i).adjlistIn.size(); ++j) {
        if (nodes.get(i).adjlistIn.get(j).active)
          ++cnt;
      }
      nodes.get(i).numInFromActive = cnt;
      // Determine set S start nodes
      if (nodes.get(i).numInFromActive == 0) {
        System.out.print("Removing node: ");
        System.out.println(nodes.get(i).toString());
        nodes.get(i).active = false;
        nodes.remove(i);
        topoOrder();
      }


    }

    // Every edge goes from lower val node to higher val node

    // If list of nodes is not zero return false
    return false;
  }
}
