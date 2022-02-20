import java.util.ArrayList;

// Authors: Olin Ruppert-Bousquet / Nick Hanna

public class Graph {
  ArrayList<Node> nodes;
  ArrayList<Node> visited = new ArrayList<>();

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
  int countInEdges(int i) {
    int cnt = 0;
    for (int j = 0; j < nodes.get(i).adjlistIn.size(); ++j) {
      if (nodes.get(i).adjlistIn.get(j).active)
        ++cnt;
    }
    return cnt;
  }

  public void printNodes() {
    for (int i = 0; i < nodes.size(); ++i) {
      if (nodes.get(i).active) {
        System.out.print("node ");
        System.out.print(nodes.get(i).toString());
        System.out.print(": #incoming edges from active nodes = ");
        System.out.println(countInEdges(i));
      }
    }
    System.out.println("\n");
  }
  public void makeActive() {
    for (Node node : nodes) {
      if (!visited.contains(node))
        node.active = true;
    }
  }
  // Send list of starting nodes
  public ArrayList <Node> startNodes() {
    ArrayList <Node> startNodes = new ArrayList<>();
    for (int i = 0; i < nodes.size(); ++i) {
      if (countInEdges(i) == 0 && nodes.get(i).active)
        startNodes.add(nodes.get(i));
    }
    return startNodes;

  }
  boolean checkActive() {
    boolean nonCycle = true;
    for (Node nodes : nodes) {
      if (nodes.active)
        nonCycle = false;
    }
    return nonCycle;
  }
  // V2 Non recursive implementation
  public boolean topoOrder() {
    // Begin by setting all nodes to active
    makeActive();

    // While some nodes have no incoming edges
    // Print and loop
    while (startNodes().size() != 0) {
      printNodes();
      // Add node to seen and remove it
      visited.add(startNodes().get(0));
      startNodes().get(0).active = false;
    }
    // Check if graph is cycle
    boolean nonCycle = checkActive();
    if (nonCycle) {
      System.out.print("Topological Order: ");
      for (Node node: visited) {
        System.out.print(node);
        System.out.print(" ");
      }
      System.out.println("\n");
    } else {
      System.out.print("No Topological Order Found");
    }
    return nonCycle;
  }
}

