import java.util.ArrayList;

public class Graph {
  ArrayList<Node> nodes;
  boolean notCycle = true;

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
    for (Node n : this.nodes) {
      System.out.print("Node " + n.name + ":");
      for (Node n2 : n.adjlistOut)
        System.out.print(" " + n2.name);
      System.out.print(" |");
      for (Node n2 : n.adjlistIn)
        System.out.print(" " + n2.name);
      System.out.println();
    }
  }

  public boolean topoOrder() {

    int i;
    boolean result = true;
    // Determine incoming active edges
    // Set number of incoming edges from active nodes

    // Print each iteration
    for (i = 0; i < nodes.size(); ++i) {
      int cnt = 0;
      for (int j = 0; j < nodes.get(i).adjlistIn.size(); ++j) {
        if (nodes.get(i).adjlistIn.get(j).active)
          ++cnt;
      }
      nodes.get(i).numInFromActive = cnt;
      // Print out results of edges from graph
      System.out.print("node ");
      System.out.print(nodes.get(i).toString());
      System.out.print(": #incoming edges from active nodes = ");
      System.out.println(nodes.get(i).numInFromActive);
      // Set all nodes to active that remain in the graph
      nodes.get(i).active = true;
    }

    // Find starting node(s)
    boolean active = false;
    for (i = 0; i < nodes.size(); ++i) {
      // Delete start node and call recursively
      if (nodes.get(i).numInFromActive == 0 && !active) {
        active = true;
        System.out.print("Removing node: ");
        System.out.println(nodes.get(i).toString());
        nodes.get(i).active = false;
        nodes.remove(i);
      }
    }
    // Recursively call if conditions not met
    if (!active && nodes.size() != 0) {
      notCycle = false;
      System.out.println("No topological order found");
    }

    if (active && nodes.size() != 0) {
      topoOrder();
    }
    return notCycle;
  }

//  public boolean topoOrder() {
//    int i, iterator = 0;
//    // Set all nodes initially as active.
//    for (Node node : nodes) {
//      node.active = true;
//    }
//    // Set number of incoming edges from active nodes
//    while (iterator < nodes.size()) {
//      for (i = 0; i < nodes.size(); ++i) {
//
//        int cnt = 0;
//        // Count number of incoming edges and assign
//        for (int j = 0; j < nodes.get(i).adjlistIn.size(); ++j) {
//          if (nodes.get(i).active)
//            if (nodes.get(i).adjlistIn.get(j).active)
//              ++cnt;
//        }
//        nodes.get(i).numInFromActive = cnt;
//        // Print out results of edges from graph
//        System.out.print("node ");
//        System.out.print(nodes.get(i).toString());
//        System.out.print(": #incoming edges from active nodes = ");
//        System.out.println(nodes.get(i).numInFromActive);
//        // Set all nodes to active that remain in the graph
//        nodes.get(i).active = true;
//      }
//      // Check if node has no incoming edges
//      // If so add to set startSet
//      boolean active = false;
//      for (i = 0; i < nodes.size(); ++i) {
//        // Delete start node
//        if (nodes.get(i).numInFromActive == 0 && !active) {
//          active = true;
//          System.out.print("Removing node: ");
//          System.out.println(nodes.get(i).toString());
//          nodes.get(i).active = false;
//          for (Node node : nodes.get(i).adjlistIn) {
//            if (nodes.get(i).adjlistIn.contains(node))
//              --node.numInFromActive;
//          }
//          iterator++;
//        }
//      }
//    }
//    return true;
//  }
}
