import java.util.ArrayList;

// Authors: Olin Ruppert-Bousquet / Nick Hanna

public class Graph {
  ArrayList<Node> nodes;
  ArrayList<Node> noIncomingEdges = new ArrayList<Node>();
  ArrayList<Integer> topOrder = new ArrayList<Integer>();
  boolean notCycle = true;
  boolean end = false;
  int numActive = 0;

  public Graph() {
    this.nodes = new ArrayList<Node>();
  }

  public void addNode(Node n) {
    ++numActive;
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
  int countInEdges(int i) {
    int cnt = 0;
    for (int j = 0; j < nodes.get(i).adjlistIn.size(); ++j) {
      if (nodes.get(i).adjlistIn.get(j).active)
        ++cnt;
    }
    return cnt;
  }

  public void printNode(int index) {
    System.out.print("node ");
    System.out.print(nodes.get(index).toString());
    System.out.print(": #incoming edges from active nodes = ");
    System.out.println(nodes.get(index).numInFromActive);
  }
  public void printTopo() {
    if (!end) {
      int maxLen = noIncomingEdges.size();
      int i = 0;
      System.out.print("Topological Order: ");
      for (Node node : noIncomingEdges) {
        ++i;
        System.out.print(node.name);
        if (i < maxLen)
          System.out.print("->");
      }
      System.out.print("\n");
    }
  }

  public boolean topoOrder() {

    int i;
    boolean result = true;
    for (i = 0; i < nodes.size(); ++i) {
      int cnt = 0;
      // Determine incoming active edges and
      // Set number of incoming edges from active nodes
      nodes.get(i).numInFromActive = countInEdges(i);
      if (nodes.get(i).numInFromActive == 0)
        if (!noIncomingEdges.contains(nodes.get(i)))
          noIncomingEdges.add(nodes.get(i));
      // Print out results of edges from graph
      printNode(i);
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
      System.out.println("\n<------No topological order found------>");

    }

    if (active && nodes.size() != 0) {
      topoOrder();
    }
    if (active && nodes.size() == 0)
      printTopo();
      end = true;
    return notCycle;
  }
}

